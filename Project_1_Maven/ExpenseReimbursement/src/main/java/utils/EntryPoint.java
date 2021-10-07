package utils;

import java.sql.Connection;

import controllers.EmployeeController;
import controllers.ReimbursementController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class EntryPoint {

	    
 public static void main(String[] args) {

     Javalin app = Javalin.create().start(8000);
     
     app.after(ctx -> {
    	 ctx.res.addHeader("Access-Control-Allow-Origin", "null");
     });
     
     EmployeeController.init(app);
     ReimbursementController.init(app);
     app.config.addStaticFiles("/static", Location.CLASSPATH);
    
        
}
 
}

