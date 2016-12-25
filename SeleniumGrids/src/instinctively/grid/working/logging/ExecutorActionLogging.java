package instinctively.grid.working.logging;
import java.io.IOException;

import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.Response;

public class ExecutorActionLogging implements CommandExecutor {
	private CommandExecutor executor;

	public ExecutorActionLogging(CommandExecutor executor) {
		this.executor = executor;
	}

	@Override
	public Response execute(Command command) throws IOException {
		//Simulating a before event
		if (DriverCommand.GET.equals(command.getName())) {
			System.out.println("before action execution : " + command.getName());
		}

		// This is where the actual event
		Response response = executor.execute(command);

		//Simulating an after event
		if (DriverCommand.GET.equals(command.getName())) {
			System.out.println("after action execution : " + command.getName());
		}
		return response;
	}
}