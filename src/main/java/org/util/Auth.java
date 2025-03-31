package org.util;

import org.Entity.NhanVien;

public class Auth {
    public static NhanVien user=null;
    public static void clear(){
        Auth.user=null;
    }
    public static boolean isLogin(){
        return Auth.user !=null;
    }
    public static boolean isManager(){
        return Auth.isLogin() && user.isVaiTro();
    }
}
