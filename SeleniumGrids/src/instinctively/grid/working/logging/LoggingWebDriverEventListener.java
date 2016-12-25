package instinctively.grid.working.logging;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class LoggingWebDriverEventListener extends AbstractWebDriverEventListener{
 
    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        LoggerFactory.getLogger(getClass()).info("Loading url " + url);
    }
 
    public void bindLogName(String log, String folderName) {
        String path = new File(folderName).getAbsolutePath() + File.separator;
        MDC.put("fileName", path + log);
    }
 
    public void unbind() {
        MDC.remove("fileName");
    }
}