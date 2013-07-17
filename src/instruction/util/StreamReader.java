package instruction.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class StreamReader extends Thread {
	private InputStream input;
	private List<String> lines = new ArrayList<String>();

	public StreamReader(InputStream input) {
		this.input = input;
		setDaemon(true);
		setName("process stream reader");
	}

	@Override
	public void run() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				input));
		try {
			do {
				String line = reader.readLine();
				if (line != null) {
					this.lines.add(line);
				} else {
					return;
				}
			} while (true);
		} catch (IOException e) {
			return;
		}
	}

	public String getMessage() {
		return StringUtils.join(this.lines, "\r\n");
	}
}
