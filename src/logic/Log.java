package logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

/**
 * A basic logging system. Must be shutdown at program termination.<br>
 * <br>
 * .add() OR .add(false) <br>
 * To add a non-error message entry to log<br>
 * <br>
 * .add(true)<br>
 * To add error message to log<br>
 * <br>
 * .shutdown()<br>
 * To safely shutdown the Logging System<br>
 * <hr>
 * Console Error Codes:<br>
 * Constructor Errors:<br>
 * - 0001 Writer IO Exception
 * - 0002 General Exception barely handled
 * <hr>
 * .add() Errors:<br>
 * - 0003 Log write failed<br>
 * - 0004 Null Pointer Exception
 * <hr>
 * .shutdown() Errors:<br>
 * - 0005 Writer close error
 * <hr>
 * 
 * @author Peter Marley
 *
 */
public class Log {

	private String filename;
	private File f;
	private FileWriter fw;
	private BufferedWriter bw;

	private Calendar cal;
	private String date;
	private String time;
	private String datestamp;

	///////////////////////////////////
	//			CONSTRUCTOR			//
	/////////////////////////////////

	/**
	 * Creates a Log system in the specified directory
	 * 
	 * @param filepath
	 */
	public Log(String filepath) throws InstantiationError {
		this.updateDatestamp();
		this.setFilename(filepath);
		this.setFile();
		InstantiationError e = null;
		try {
			this.setWriter();
			this.add("Log Initialised Successfully!");
		} catch (IOException ioe) {
			String msg = "Log WARNING: Writer IO Exception (0001)";
			System.err.println(msg);
			e = new InstantiationError(msg);
			throw e;
		} catch (Exception ue) {
			String msg = "Log WARNING: General Exception barely handled (0002)";
			System.err.println(msg);
			e = new InstantiationError(msg);
			throw e;
		}
	}

	///////////////////////////////////
	//			LOG METHODS			//
	/////////////////////////////////

	/**
	 * Add an entry to the log
	 * @param entry A String - The actual message to log
	 * @param isAnError A boolean - is the message an error message?
	 */
	public void add(String entry, boolean isAnError) {
		try {
			bw.write(String.format("%s%n \t%s%n\t%s: %s%n%n", getDateTime(), Thread.currentThread().getStackTrace()[3], ((isAnError) ? "ERR" : "MSG"), entry));
		} catch (IOException e) {
			System.err.println("Log WARNING: Log write failed (0003)");
			e.printStackTrace();
		} catch (NullPointerException npe) {
			System.err.println("Log WARNING: Null Pointer Exception (0004)");
		}
		//		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		//		for (int i = 0; i < ste.length; i++) {
		//			System.out.println(i + ": " + ste[i]);
		//		}
	}

	/**
	 * Add a non-error entry to the log
	 * @param entry A String - The actual message to log
	 */
	public void add(String entry) {
		this.add(entry, false);
	}

	/**
	 * Safely shuts the Log system down
	 */
	public void shutdown() {
		try {
			this.add("Shutting down logging system");
			bw.close();
		} catch (IOException e) {
			System.err.println("Log WARNING: Writer close error (0005)");
		}
	}

	///////////////////////////////////
	//		GETTERS N SETTERS		//
	/////////////////////////////////

	/**
	 * Init file writers
	 * 
	 * @throws IOException
	 */
	private void setWriter() throws IOException {
		fw = new FileWriter(f);
		bw = new BufferedWriter(fw);
	}

	/**
	 * Sets the file
	 */
	private void setFile() {
		f = new File(this.filename);
	}

	/**
	 * Update the time and date
	 */
	private void updateDatestamp() {
		cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);

		this.date = String.format("[%d-%02d-%02d]", year, month, day);
		this.time = String.format("[%d-%d-%02d]", hour, minute, second);
		this.datestamp = date + "." + time;
	}

	/**
	 * @param set the filename for the log file.
	 */
	private void setFilename(String filepath) {
		this.filename = filepath + "logfile" + this.getDateTime() + ".txt";
	}

	/**
	 * 
	 * @return Get a formatted date/time String in the format "YYYY-MM-DD.HH-MM-SS"
	 */
	private String getDateTime() {
		updateDatestamp();
		return this.datestamp;
	}

	/**
	 * 
	 * @return Get the Log filename
	 */
	public String getFilename() {
		return this.filename;
	}

}
