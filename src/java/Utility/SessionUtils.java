package Utility;


import Entity.User;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static void setUser(User user) {
        HttpSession session = getSession();
        if (user != null) {
            session.setAttribute("user", user);
            setUserId(user);
            isAdmin();
        }
    }

    public static User getUser() {
        HttpSession session = getSession();
        if (session != null) {
            if (session.getAttribute("user") != null) {
                return (User) session.getAttribute("user");
            }
        } else {
            return null;
        }
                    return null;

    }

    public static Boolean isAdmin() {
        HttpSession session = getSession();
        if (SessionUtils.getUser() != null) {
            if (SessionUtils.getUser().getYetki().isAdmin()) {
                session.setAttribute("isAdmin", 1);
                return true;
            }
        }
        session.setAttribute("isAdmin", 0);

        return false;
    }

    public static void setUserId(User user) {
        HttpSession session = getSession();
        if (user != null) {
            session.setAttribute("userid", user.getUsername());
        }
    }

    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("userid");
        } else {
            return null;
        }
    }

    public static Boolean isLoggedin() {
        HttpSession session = getSession();
        if (SessionUtils.getUser() != null) {
            return true;
        }

        return false;
    }

}
