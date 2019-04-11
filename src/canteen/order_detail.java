/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canteen;

/**
 *
 * @author Himanshu
 */
public class order_detail {
    int uid;
    String date,order_detail;
    int ordervalue;
    int order_status;
    int priority;
    public order_detail(int uid,String order_detail,String date,int ordervalue,int order_status,int priority)       
    {
    this.uid=uid;
    this.order_detail=order_detail;
    this.date=date;
    this.ordervalue=ordervalue;
    this.order_status=order_status;
    this.priority=priority;
    }
    public int getuid()
    {
      return uid;  
    }
     public String getorder_detail()
    {
        return order_detail;
    }
    public String getdate()
    {
        return date;
    }
    public int getordervalue()
    {
        return ordervalue;
    }
    public int getorder_status(){
        return order_status;
    }
    public int getpriority()
    {
        return priority;
    }
}
