package nodes;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.LoginUser;
import model.Schedule;
import model.Ticket;
import model.enums.TICKET_STATUS;
import service.TicketSer;
import view.MainFrame;

import java.util.List;

public class TicketImage extends ImageView {
    private Ticket ticket;
    private List<Ticket> chosedTickets;
    private static Image[] images = {
            //new Image("",35,35,true,true),
            null,
            new Image("file:Resource/seat/white.png", 35, 35, true, true),
            new Image("file:Resource/seat/red.png", 35, 35, true, true),
            new Image("file:Resource/seat/green.png", 35, 35, true, true),
            new Image("file:Resource/seat/gray.png", 35, 35, true, true),
            new Image("file:Resource/loading3.gif", 35, 35, true, true)
    };

    public TicketImage(Ticket ticket, List<Ticket> tickets, Long timeStamp) {
        this.ticket = ticket;
        this.chosedTickets = tickets;
        if (ticket.getStatus().equals(TICKET_STATUS.AVL)) {
            if (timeStamp - ticket.getLockedTime() > Ticket.lockTime) {
                setForSale();
            } else if (ticket.getLockedUID() == LoginUser.getLoginUser().getUid()) {
                chosedTickets.add(ticket);
                setChosed();
            } else {
                setLocked();
            }
        }else if(ticket.getStatus().equals(TICKET_STATUS.SOLD)){
            setSaled();
        }
    }

    private void lockTicket() {
        //选票！ 是选票！选票！！！！

        if (chosedTickets.size() >= 5) {
            MainFrame.popupMessage("一次性最多购买5张票！");
            return;
        }
        new Thread(new Task<Boolean>() {
            @Override
            protected Boolean call() {
                //TODO 客户端用户身份易于造假 应该从服务器端设置id
                ticket.setLockedUID(LoginUser.getLoginUser().getUid());
                return new TicketSer().lockedTicked(ticket);
            }

            @Override
            protected void running() {
                setImage(5);
                super.running();
            }

            @Override
            protected void succeeded() {
                if (getValue()) {
                    chosedTickets.add(ticket);
                    setChosed();
                } else {
                    waitForRushToBuy();
                    MainFrame.popupMessage("此座位已被其他用户锁定！可在十秒钟之后再次抢座！",3000);
                }
                super.succeeded();
            }

            @Override
            protected void failed() {
                setForSale();
                super.failed();
            }
        }).start();

    }

    public void unLockTicket() {
        new Thread(new Task<Boolean>() {
            @Override
            protected Boolean call() {
                ticket.setLockedUID(LoginUser.getLoginUser().getUid());
                return new TicketSer().unLockTicked(ticket);
            }

            @Override
            protected void running() {
                setImage(5);
                super.running();
            }

            @Override
            protected void succeeded() {
                chosedTickets.remove(ticket);
                if (getValue()) {
                    setForSale();
                } else {
                    setLocked();
                    MainFrame.popupMessage("该座位已被其他用户锁定！再次点击可以抢购！",3000);
                }
                super.succeeded();
            }

            @Override
            protected void failed() {
                setChosed();
                MainFrame.popupMessage("这都失败只能是人品问题了。。");
                super.failed();
            }
        }).start();
    }

//    private void setImage() {
//        this.setImage(images[ticket.getStatus().getIndex()]);
//    }

    private void setImage(int index) {
        this.setOnMouseClicked(null);
        this.setImage(images[index]);
    }

    private void setChosed() {
        //设置为被选择状态 也就是被自己锁定(绿色 点击将触发解锁操作)
        setImage(3);
        setOnMouseClicked(e -> unLockTicket());
    }

    private void setForSale() {
        //设置为待售状态(白色 点击将触发锁票操作)
        setImage(1);
        setOnMouseClicked(e -> lockTicket());
    }

    private void waitForRushToBuy() {
        setOnMouseClicked(null);
        new Thread(new Task<Void>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void succeeded() {
                setLocked();
                super.succeeded();
            }
        }).start();

    }

    private void setLocked() {
        //设置被其他用户锁定状态(灰色 可点击抢购)
        setImage(4);
        setOnMouseClicked(e -> lockTicket());
    }
    private void setSaled(){
        setImage(2);
        setOnMouseClicked(null);
    }
//    private void recTicket(){
//        //选择座位后 如果数据库中座位状态没有修改成功  则使用此方法将座位图标以及事件恢复
//        setImage();
//        this.setOnMouseClicked(e -> {this.changeStatus();});
//    }
}
