/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package browsermator4.com;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 *
 * @author pcalkins
 */
public class UpArrowKeyAction extends BMAction {
    UpArrowKeyAction() 
    {
     this.Type = "Up Arrow Key";     
}
    @Override
    public void SetGuts()
    {
   this.Guts = "  try\n" +
"        {\n" +
"\n" +
" WebElement element = driver.switchTo().activeElement();\n" +
" element.sendKeys(Keys.ARROW_UP);\n" +
"this.Pass = true; \n" +
"        }\n" +
"        catch (Exception ex)\n" +
"                {\n" +
"                    this.Pass = false;\n" +
"                } ";
   
    }
  @Override
    public void RunAction(WebDriver driver)
    {
        try
        {

 WebElement element = driver.switchTo().activeElement();
 element.sendKeys(Keys.ARROW_UP);
this.Pass = true; 
        }
        catch (Exception ex)
                {
                    this.Exception = ex.toString();
                    this.Pass = false;
                }
     
    }    
}
