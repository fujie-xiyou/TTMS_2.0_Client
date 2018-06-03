package view;

import service.StudioSer;

import java.util.List;

public class Studio {
    private List<model.Studio> studios = null;
    private StudioSer studioSer = new StudioSer();
    public void mgtEntry(){
        studios = studioSer.fetchAll();
        MainFrame.top.remove(MainFrame.top);
    }
}
