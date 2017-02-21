package dev.source.utility;

import org.jnativehook.keyboard.NativeKeyEvent;

public class Commons {
  public static boolean isLetter(int keyCode) {
    if (keyCode == NativeKeyEvent.VC_A || keyCode == NativeKeyEvent.VC_B
        || keyCode == NativeKeyEvent.VC_C || keyCode == NativeKeyEvent.VC_D
        || keyCode == NativeKeyEvent.VC_E || keyCode == NativeKeyEvent.VC_F
        || keyCode == NativeKeyEvent.VC_G || keyCode == NativeKeyEvent.VC_H
        || keyCode == NativeKeyEvent.VC_I || keyCode == NativeKeyEvent.VC_J
        || keyCode == NativeKeyEvent.VC_K || keyCode == NativeKeyEvent.VC_L
        || keyCode == NativeKeyEvent.VC_M || keyCode == NativeKeyEvent.VC_N
        || keyCode == NativeKeyEvent.VC_O || keyCode == NativeKeyEvent.VC_P
        || keyCode == NativeKeyEvent.VC_Q || keyCode == NativeKeyEvent.VC_R
        || keyCode == NativeKeyEvent.VC_S || keyCode == NativeKeyEvent.VC_T
        || keyCode == NativeKeyEvent.VC_U || keyCode == NativeKeyEvent.VC_V
        || keyCode == NativeKeyEvent.VC_W || keyCode == NativeKeyEvent.VC_X
        || keyCode == NativeKeyEvent.VC_Y || keyCode == NativeKeyEvent.VC_Z) {
      return true;
    }
    return false;

  }
}
