/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package browsermator4.com;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 *
 * @author pcalkins
 */
public class DragAndDropAction extends BMAction {
  DragAndDropAction (String ToDragXPATH, String ToTargetXPATH)
    {
        this.Type = "Drag From ID to ID";
        
        this.Variable1 = ToDragXPATH;
        this.Variable2 = ToTargetXPATH;
        
    }
  @Override
    public void SetGuts()
    {
        this.Guts = "     wait = new WebDriverWait(driver, Duration.ofSeconds(ec_Timeout));\n" +
" try\n" +
" {\n" +
"  \n" +
" WebElement dragElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(" + this.Variable1+ ")));\n" +
" WebElement dropElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(" + this.Variable2+ ")));\n" +
"\n" +
" \n" +
" String simulateFunction = \"function simulateDragDrop(sourceNode, destinationNode) {\\n\" +\n" +
"\"    var EVENT_TYPES = {\\n\" +\n" +
"\"        DRAG_END: 'dragend',\\n\" +\n" +
"\"        DRAG_START: 'dragstart',\\n\" +\n" +
"\"        DROP: 'drop'\\n\" +\n" +
"\"    }\\n\" +\n" +
"\"\\n\" +\n" +
"\"    function createCustomEvent(type) {\\n\" +\n" +
"\"        var event = new CustomEvent(\\\"CustomEvent\\\")\\n\" +\n" +
"\"        event.initCustomEvent(type, true, true, null)\\n\" +\n" +
"\"        event.dataTransfer = {\\n\" +\n" +
"\"            data: {\\n\" +\n" +
"\"            },\\n\" +\n" +
"\"            setData: function(type, val) {\\n\" +\n" +
"\"                this.data[type] = val\\n\" +\n" +
"\"            },\\n\" +\n" +
"\"            getData: function(type) {\\n\" +\n" +
"\"                return this.data[type]\\n\" +\n" +
"\"            }\\n\" +\n" +
"\"        }\\n\" +\n" +
"\"        return event\\n\" +\n" +
"\"    }\\n\" +\n" +
"\"\\n\" +\n" +
"\"    function dispatchEvent(node, type, event) {\\n\" +\n" +
"\"        if (node.dispatchEvent) {\\n\" +\n" +
"\"            return node.dispatchEvent(event)\\n\" +\n" +
"\"        }\\n\" +\n" +
"\"        if (node.fireEvent) {\\n\" +\n" +
"\"            return node.fireEvent(\\\"on\\\" + type, event)\\n\" +\n" +
"\"        }\\n\" +\n" +
"\"    }\\n\" +\n" +
"\"\\n\" +\n" +
"\"    var event = createCustomEvent(EVENT_TYPES.DRAG_START)\\n\" +\n" +
"\"    dispatchEvent(sourceNode, EVENT_TYPES.DRAG_START, event)\\n\" +\n" +
"\"\\n\" +\n" +
"\"    var dropEvent = createCustomEvent(EVENT_TYPES.DROP)\\n\" +\n" +
"\"    dropEvent.dataTransfer = event.dataTransfer\\n\" +\n" +
"\"    dispatchEvent(destinationNode, EVENT_TYPES.DROP, dropEvent)\\n\" +\n" +
"\"\\n\" +\n" +
"\"    var dragEndEvent = createCustomEvent(EVENT_TYPES.DRAG_END)\\n\" +\n" +
"\"    dragEndEvent.dataTransfer = event.dataTransfer\\n\" +\n" +
"\"    dispatchEvent(sourceNode, EVENT_TYPES.DRAG_END, dragEndEvent)\\n\" +\n" +
"\"} var toDrag =document.getElementById('\" + this.Variable1 + \"'); var toDrop = document.getElementById('\" + this.Variable2 + \"');\";\n" +
"\n" +
"  ((JavascriptExecutor)driver).executeScript(simulateFunction + \"simulateDragDrop(toDrag, toDrop);\");\n" +
"   \n" +
"\n" +
"\n" +
"     this.Pass = true;\n" +
" }\n" +
" catch (Exception e)\n" +
" {\n" +
"  this.Pass = false;\n" +
"  System.out.println(\"Exception: \" + e);\n" +
"  \n" +
" }";
    }
    @Override
    public void RunAction(WebDriver driver)
    {
      wait = new WebDriverWait(driver, Duration.ofSeconds(ec_Timeout));
 try
 {
  
 WebElement dragElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(this.Variable1)));
 WebElement dropElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(this.Variable2)));

 
 String simulateFunction = "function simulateDragDrop(sourceNode, destinationNode) {\n" +
"    var EVENT_TYPES = {\n" +
"        DRAG_END: 'dragend',\n" +
"        DRAG_START: 'dragstart',\n" +
"        DROP: 'drop'\n" +
"    }\n" +
"\n" +
"    function createCustomEvent(type) {\n" +
"        var event = new CustomEvent(\"CustomEvent\")\n" +
"        event.initCustomEvent(type, true, true, null)\n" +
"        event.dataTransfer = {\n" +
"            data: {\n" +
"            },\n" +
"            setData: function(type, val) {\n" +
"                this.data[type] = val\n" +
"            },\n" +
"            getData: function(type) {\n" +
"                return this.data[type]\n" +
"            }\n" +
"        }\n" +
"        return event\n" +
"    }\n" +
"\n" +
"    function dispatchEvent(node, type, event) {\n" +
"        if (node.dispatchEvent) {\n" +
"            return node.dispatchEvent(event)\n" +
"        }\n" +
"        if (node.fireEvent) {\n" +
"            return node.fireEvent(\"on\" + type, event)\n" +
"        }\n" +
"    }\n" +
"\n" +
"    var event = createCustomEvent(EVENT_TYPES.DRAG_START)\n" +
"    dispatchEvent(sourceNode, EVENT_TYPES.DRAG_START, event)\n" +
"\n" +
"    var dropEvent = createCustomEvent(EVENT_TYPES.DROP)\n" +
"    dropEvent.dataTransfer = event.dataTransfer\n" +
"    dispatchEvent(destinationNode, EVENT_TYPES.DROP, dropEvent)\n" +
"\n" +
"    var dragEndEvent = createCustomEvent(EVENT_TYPES.DRAG_END)\n" +
"    dragEndEvent.dataTransfer = event.dataTransfer\n" +
"    dispatchEvent(sourceNode, EVENT_TYPES.DRAG_END, dragEndEvent)\n" +
"} var toDrag =document.getElementById('" + this.Variable1 + "'); var toDrop = document.getElementById('" + this.Variable2 + "');";

  ((JavascriptExecutor)driver).executeScript(simulateFunction + "simulateDragDrop(toDrag, toDrop);");
   


     this.Pass = true;
 }
 catch (Exception e)
 {
  this.Pass = false;
  System.out.println("Exception: " + e);
  
 }
    }  
       
}
