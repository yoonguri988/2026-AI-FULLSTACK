package com.the703.v1;

public class AppStatus {
	private boolean running = true;

    public boolean isRunning() { return running; }
    public void stop() { this.running = false; }
}
