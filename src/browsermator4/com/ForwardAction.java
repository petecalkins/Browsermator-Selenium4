package browsermator4.com;



import org.openqa.selenium.WebDriver;


public class ForwardAction extends BMAction
{
  ForwardAction()
    {
       this.Type = "Forward Action"; 
    }
  @Override
  public void SetGuts()
  {
   this.Guts = "\n  try\n" +
"      {\n" +
"        driver.navigate().forward();\n" +
"        this.Pass = true;\n" +
"      }\n" +
"        catch (Exception e)\n" +
" {\n" +
"  this.Pass = false;\n" +
"  \n" +
" }";   
  }
  @Override
    public void RunAction(WebDriver driver)
    {
      try
      {
        driver.navigate().forward();
        this.Pass = true;
      }
        catch (Exception e)
 {
     this.Exception = e.toString();
  this.Pass = false;
  
 }
      
    }
}