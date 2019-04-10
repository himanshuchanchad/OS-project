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
    int id;
    String date;
    int ordervalue;
    int order_status;
    int priority;
    public order_detail(int uid,int id,String date,int ordervalue,int order_status,int priority)       
    {
    this.uid=uid;
    this.id=id;
    this.date=date;
    this.ordervalue=ordervalue;
    this.order_status=order_status;
    this.priority=priority;
    }
    public int getuid()
    {
      return uid;  
    }
     public int getid()
    {
        return id;
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
