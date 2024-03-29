
package browsermator4.com;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PastePasswordAtXPATHAction extends BMAction
{

  PastePasswordAtXPATHAction (String TargetXPATH, String ToType, Boolean BoolVal1)
    {
        this.Type = "Paste Password at XPATH";
        
        this.Variable1 = TargetXPATH;
    
            this.Variable2 = ToType;
     this.BoolVal1 = BoolVal1;
    }
  @Override
    public void SetGuts()
    {
     this.Guts = " try\n" +
" {\n" +
"  this.Pass = true;      \n" +
"     \n" +
"if (this.Variable2.length()>0)\n" +
" {\n" +
"      wait = new WebDriverWait(driver, Duration.ofSeconds(ec_Timeout));     \n" +
"       WebElement element  = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(" + this.Variable1+ ")));  \n" +
"      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();\n" +
"    Transferable transferable = new StringSelection(" + this.Variable2+");\n" +
"    clipboard.setContents(transferable, null);  \n" +
"   \n" +
"    String vKey = \"v\";\n" +
"\n" +
"            try\n" +
"            {\n" +
"        element.sendKeys(Keys.CONTROL , vKey);    \n" +
"            }\n" +
"            catch (Exception ex)\n" +
"            {\n" +
"            this.Pass = false; \n" +
"            }     \n" +
"\n" +
"if (this.BoolVal1.equals(true))\n" +
"{\n" +
"      try\n" +
"  {\n" +
"Thread.sleep((long)(Math.random() * 150));\n" +
"  }\n" +
"  catch (Exception ex)\n" +
"  {\n" +
"      System.out.println (\"Exception when sleeping random: \" + ex.toString());\n" +
"  }\n" +
"    try\n" +
"    {\n" +
"element.sendKeys(Keys.RETURN);\n" +
"    }\n" +
"    catch (Exception ex)\n" +
"    {\n" +
"       this.Pass = false; \n" +
"    }\n" +
"}\n" +
" }\n" +
"\n" +
"else\n" +
"{\n" +
"    this.Pass = false;\n" +
"}\n" +
"\n" +
"    \n" +
" }\n" +
" catch (Exception e)\n" +
" {\n" +
"  System.out.println (e.toString());\n" +
"  this.Pass = false;\n" +
"  \n" +
" }\n" +
"    }  ";
    }
    @Override
    public void RunAction(WebDriver driver)
    {
  
 try
 {
  this.Pass = true;      
     
if (this.Variable2.length()>0)
 {
      wait = new WebDriverWait(driver, Duration.ofSeconds(ec_Timeout));     
       WebElement element  = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.Variable1)));  
      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    Transferable transferable = new StringSelection(this.Variable2);
     Transferable emptytransferable = new StringSelection("");
    clipboard.setContents(transferable, null);  
   
    String vKey = "v";

            try
            {
        element.sendKeys(Keys.CONTROL , vKey);   
        clipboard.setContents(emptytransferable, null);
            }
            catch (Exception ex)
            {
            this.Pass = false; 
            clipboard.setContents(emptytransferable, null);
            }     

if (this.BoolVal1.equals(true))
{
      try
  {
Thread.sleep((long)(Math.random() * 150));
  }
  catch (Exception ex)
  {
    this.Exception = ex.toString();
  }
    try
    {
element.sendKeys(Keys.RETURN);
    }
    catch (Exception ex)
    {
        this.Exception = ex.toString();
       this.Pass = false; 
    }
}
 }

else
{
    this.Pass = false;
}

    
 }
 catch (Exception e)
 {
  this.Exception = e.toString();
  this.Pass = false;
  
 }
    }  
    
            
   
    }
