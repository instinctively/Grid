package instinctively.grid.working.threading.parallel;

import org.testng.IObjectFactory2;

public class MyObjectFactory implements IObjectFactory2 {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyObjectFactory() {
        System.err.println("Custom Factory being created");
    }
 
    @Override
    public Object newInstance(Class<?> cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}