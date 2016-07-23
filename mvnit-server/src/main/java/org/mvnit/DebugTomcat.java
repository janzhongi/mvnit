package org.mvnit;

/**
 * Created by zhongjian on 7/24/16.
 */


import java.io.File;
import org.apache.catalina.Context;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.deploy.ErrorPage;
import org.apache.catalina.startup.Tomcat;

public class DebugTomcat {

    public static void main(String[] args) throws Exception {
        int port = 7081;
        if (args.length >= 1) {
            port = Integer.parseInt(args[0]);
        }

        String webBase = new File("../mvnit-ui").getAbsolutePath();
//        if (new File(webBase, "WEB-INF").exists() == false) {
//            throw new RuntimeException("In order to launch Kylin web app from IDE, please run:\ncp -rf extensions/server/src/main/webapp/WEB-INF webapp/app/");
//        }

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.setBaseDir(".");

        // Add AprLifecycleListener
        StandardServer server = (StandardServer) tomcat.getServer();
        AprLifecycleListener listener = new AprLifecycleListener();
        server.addLifecycleListener(listener);

        Context webContext = tomcat.addWebapp("/", webBase);
        ErrorPage notFound = new ErrorPage();
        notFound.setErrorCode(404);
        notFound.setLocation("/index.html");
        webContext.addErrorPage(notFound);
        webContext.addWelcomeFile("index.html");


//
//        String serverBase = new File("src/main/webapp/").getAbsolutePath();
//        if (new File(serverBase, "WEB-INF").exists() == false) {
//            throw new RuntimeException("No WEB-INF found.");
//        }
//        Context serverContext = tomcat.addWebapp("/saiku", serverBase);
//        notFound.setErrorCode(404);
//        webContext.addErrorPage(notFound);


        // tomcat start
        tomcat.start();
        tomcat.getServer().await();
    }

}
