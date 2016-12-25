package instinctively.grid.working.utils;
import org.openqa.grid.internal.utils.configuration.GridHubConfiguration;
import org.openqa.grid.web.Hub;

public class GridHub {

    private String host = "localhost";
    private Integer port = 4444;
    Hub gridHub = null;
    static GridHubConfiguration gridHubConfig = new GridHubConfiguration();
    GridHub() throws Exception {
    	gridHubConfig.host = host;
    	gridHubConfig.port = port;
        gridHub = new Hub(gridHubConfig);        
            gridHub.start();
    }
}