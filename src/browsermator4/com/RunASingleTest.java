/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package browsermator4.com;

import java.awt.Cursor;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.SwingWorker;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;





public class RunASingleTest extends SwingWorker <String, Integer> {
    Procedure bugtorun;
    ProcedureView thisbugview;

    
    SeleniumTestTool STAppFrame;
    SeleniumTestToolData STAppData;
    WebDriver driver;

    String BrowsermatorAppFolder;


     String waitForLoad;
     DriverFactory driverFactory;
     String targetbrowser;
     String OSType;

    BrowserMatorConfig appConfig = new BrowserMatorConfig();
  public RunASingleTest (SeleniumTestTool in_STAppFrame, SeleniumTestToolData in_STAppData, Procedure in_bugtorun, ProcedureView in_thisbugview)
          {
               this.driverFactory = new DriverFactory(in_STAppData);
             
              this.STAppFrame = in_STAppFrame;
              this.STAppData = in_STAppData;
              this.bugtorun = in_bugtorun;
              this.thisbugview = in_thisbugview;
               this.targetbrowser = STAppData.TargetBrowser;
             this.OSType = STAppData.OSType;
       
              
              STAppData.cancelled = false;

     BrowsermatorAppFolder =   System.getProperty("user.home")+File.separator+"BrowsermatorAppFolder"+File.separator;

   
          }
    public String doInBackground()
 {
     STAppData.testRunning = true;
  

   thisbugview.JButtonRunTest.setText("Running...");
  
    RunSingleTest(bugtorun, thisbugview);
    String donetext = "Run";
     return donetext;
 }
 protected void done()
 {
  STAppData.testRunning = false;
    try
    {
        String donetext = get();
      thisbugview.JButtonRunTest.setText(donetext); 
      
    }
   catch (Exception ex)
    {
      

        thisbugview.JButtonRunTest.setText("Run"); 
      
    }
   STAppFrame.setCursor(Cursor.getDefaultCursor()); 
       if (STAppData.getPromptToClose())
     {
  
   
     }
     else
     {
 try
        {
       if (driver!=null) { driver.quit(); }
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
         
        }
  
     }

          ArrayList<ActionView> ActionView = thisbugview.ActionsViewList;

 int ActionIndex = 0;

   for( ActionView TheseActionViews : ActionView ) {


    LocalDateTime stringtime = LocalDateTime.now();
    
            bugtorun.ActionsList.get(ActionIndex).TimeOfTest = stringtime;
       boolean TestState = bugtorun.ActionsList.get(ActionIndex).Pass;
       if (TestState==true)
       {
           thisbugview.ActionsViewList.get(ActionIndex).JLabelPassFail.setText("Passed at " + stringtime);

       }
       else
       {
           thisbugview.ActionsViewList.get(ActionIndex).JLabelPassFail.setText("Fail at " + stringtime);
           
       }

       ActionIndex++;

}  
 }
 
 public void RunSingleTest(Procedure bugtorun, ProcedureView thisbugview)
 {
    
  STAppFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
  STAppData.initVarLists();
  driver = driverFactory.buildDriver();
 
   GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
       GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
       Rectangle graphicsConfigurationBounds = ge.getMaximumWindowBounds();
       int desiredWidth =  graphicsConfigurationBounds.width - 400;
       int desiredHeight =  graphicsConfigurationBounds.height;
       driver.manage().window().setPosition(new Point(0,0));
       driver.manage().window().setSize(new Dimension(desiredWidth,desiredHeight));
   int WaitTime = 0;
   int EcTimeout = 10;
  WaitTime = STAppData.getWaitTime();
  EcTimeout = STAppData.getEcTimeout();
  //timeouts still buggy.. removed
 // int Timeout = SiteTest.getTimeout();
//  int Timeout = 5;
  
// driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);
// driver.manage().timeouts().pageLoadTimeout(Timeout, TimeUnit.SECONDS);
// driver.manage().timeouts().setScriptTimeout(Timeout, TimeUnit.SECONDS);

     int totalpause = WaitTime * 1000;
 
 if (!"Dataloop".equals(thisbugview.Type))
  {
   for( BMAction ThisAction : bugtorun.ActionsList ) {
        if (STAppData.cancelled)
          {
          
             publish(bugtorun.index);
             
             break;
          }  
           if (!ThisAction.Locked)
   {
       ThisAction.setEcTimeout(EcTimeout);
   try
   {
       if (totalpause>0)
       {
        try
  {
   Thread.sleep(totalpause);  
  }
  catch (Exception ex)
  {
        System.out.println ("Exception when sleeping: " + ex.toString());
       ThisAction.Pass = false;
         
          break;
  }
       }
                      String varfieldname="";
       if (ThisAction.Variable2.contains("[stored_varname-start]"))
       {
         varfieldname = ThisAction.Variable2;
            int indexof_end_tag = varfieldname.indexOf("[stored_varname-end]");
      // assuming name of "[stored_varname-start]" and "[stored_varname-end]"
         String fieldname = varfieldname.substring(22, indexof_end_tag);
         ThisAction.Variable2 = STAppData.GetStoredVariableValue(fieldname);
          ThisAction.RunAction(driver);
          ThisAction.Variable2 = "[stored_varname-start]"+fieldname+"[stored_varname-end]";
       }
       else
       {
             if ("Pause with Continue Button".equals(ThisAction.Type))
        {
         int nothing =  ThisAction.RunAction(driver, "Actions Paused...", STAppData, 0, 0); 
         
        }
             else
             {
         ThisAction.RunAction(driver);    
             }
       }
       
      
      if (!"".equals(ThisAction.tostore_varvalue))
       {
        
           STAppData.VarHashMap.put(ThisAction.tostore_varname, ThisAction.tostore_varvalue);
   
       }
    if (ThisAction.tostore_varlist.size()>0)
       {

           STAppData.VarLists.put(ThisAction.Variable2, ThisAction.tostore_varlist);
           

       }
      
     
   
   }
   catch (Exception ex)
   {
  
        break;
     
        }
   } 
             else
      {
          ThisAction.Pass = true;
      }
   }  

}
else
{
 int number_of_rows = thisbugview.DataTable.getRowCount();
 
     for (int x = 0; x<number_of_rows; x++)
    {
         int changex = -1;
    for( BMAction ThisAction : bugtorun.ActionsList ) {
         if (STAppData.cancelled)
          {
          
             publish(bugtorun.index);
             break;
          }  
           String original_value1 = ThisAction.Variable1;
           String original_value2 = ThisAction.Variable2;
      if (!ThisAction.Locked)
   {
   ThisAction.setEcTimeout(EcTimeout);
   
               DataLoopVarParser var1Parser = new DataLoopVarParser(ThisAction.Variable1);
    DataLoopVarParser var2Parser = new DataLoopVarParser(ThisAction.Variable2);
    if (var1Parser.hasDataLoopVar==false && var2Parser.hasDataLoopVar==false)
    {
         if ("Pause with Continue Button".equals(ThisAction.Type))
        {
           String pause_message = "Paused at record " + (x+1) + " of " + number_of_rows;
          changex =  ThisAction.RunAction(driver, pause_message, STAppData, x, number_of_rows);
  
        }
         else
                   try
            {
                   if (totalpause>0)
       {
                  try
  {
  Thread.sleep(totalpause);  
  }
  catch (Exception ex)
  {
  
         System.out.println ("Exception when sleeping: " + ex.toString());
       ThisAction.Pass = false;
            publish(bugtorun.index);
          break;
  }
      
         }
                   
   
                       String varfieldname="";
       if (ThisAction.Variable2.contains("[stored_varname-start]"))
       {
         varfieldname = ThisAction.Variable2;
            int indexof_end_tag = varfieldname.indexOf("[stored_varname-end]");
      // assuming name of "[stored_varname-start]" and "[stored_varname-end]"
         String fieldname = varfieldname.substring(22, indexof_end_tag);
         ThisAction.Variable2 = STAppData.GetStoredVariableValue(fieldname);
          ThisAction.RunAction(driver);
          ThisAction.Variable2 = "[stored_varname-start]"+fieldname+"[stored_varname-end]";
       }
       else
       {
         ThisAction.RunAction(driver);    
       }
       
      
       if (!"".equals(ThisAction.tostore_varvalue))
       {
        
           STAppData.VarHashMap.put(ThisAction.tostore_varname, ThisAction.tostore_varvalue);
        
       }
      
    
      
       }
        catch (Exception ex)
     {
 
        ThisAction.Variable1 = original_value1;
        ThisAction.Variable2 = original_value2;
      
          break;
       
     }
       
       }
    else
    {
  
       
            String concat_variable="";
            String concat_variable2="";
                 if ("urllist".equals(bugtorun.DataLoopSource))
            {
 concat_variable = var1Parser.GetFullValueFromURLList(x, bugtorun.URLListData);
            }
            if ("file".equals(bugtorun.DataLoopSource))
            {
   concat_variable = var1Parser.GetFullValueFromFile(x, bugtorun.RunTimeFileSet);             
            }

 if (var1Parser.hasDataLoopVar)
 {
     ThisAction.Variable1 = concat_variable;
        if ("".equals(ThisAction.Variable1))
           {
               ThisAction.Variable1 = " ";
           }
 }
  if ("urllist".equals(bugtorun.DataLoopSource))
            {
 concat_variable2 = var2Parser.GetFullValueFromURLList(x, bugtorun.URLListData);
            }
            if ("file".equals(bugtorun.DataLoopSource))
            {
   concat_variable2 = var2Parser.GetFullValueFromFile(x, bugtorun.RunTimeFileSet);             
            }
        
   if (var2Parser.hasDataLoopVar)
 {
     ThisAction.Variable2 = concat_variable2;
     if ("".equals(ThisAction.Variable2))
           {
               ThisAction.Variable2 = " ";
           } 
 }  
     try
     {
         if (totalpause>0)
         {
         try
  {
   Thread.sleep(totalpause);  
  }
  catch (Exception ex)
  {
         System.out.println ("Exception when sleeping: " + ex.toString());
       ThisAction.Pass = false;
  
          break;
  }
         }
      ThisAction.RunAction(driver);
       ThisAction.Variable1 = original_value1;
   ThisAction.Variable2 = original_value2;

     }
     catch (Exception ex)
     {
   
       ThisAction.Variable1 = original_value1;
       ThisAction.Variable2 = original_value2;
    
          break;
       
     }
     
  }
   
      }
        else
      {
          ThisAction.Pass = true;
      }
     }
                if (changex!=x)
    {
        if (changex==-1)
        {
      
        }
        else
        {
        
        x=changex-1;
          
        }
    }
    }
   }
         if (STAppData.getPromptToClose())
     {
          Prompter thisContinuePrompt = new Prompter(STAppData.short_filename + " - Prompt to close webdriver", "Close webdriver/browser?", false,0, 0);
  
    


    
while(thisContinuePrompt.isVisible() == true){
       try
       {
 Thread.sleep(200);

       }
       catch (InterruptedException e)
               {
                  System.out.println("pause exception: " + e.toString());
                
              }
  
}
  
   
          try
 {
   if (driver != null) { driver.quit();}
 }
 catch (Exception ex)
 {
     // don't worry it should close
 }

     } 
    
 } 
    

}
