public class MMBurgers implements MMBurgersInterface 
{
    public int K=0;
    public int M=0;
    //public int freeM=0;
    public int preadded=0;
    public int preid=0;
    public int pret=0;
    public int pretime=0;
    Customerinfo abc=new Customerinfo();
    Heapsform xyz=new Heapsform();
    Queueform[] counterarray;
    Gnode[] garray;
    public boolean isEmpty()
    {
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        int h=0;
        for(h=0;h<abc.Ci;h++)
            if(abc.carray[h].cstate!=K+2)
                return false;
        return true;

    } 
    
    public void setK(int k) throws IllegalNumberException
    {
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        if(k>0)
        {
            K=k;
            counterarray=new Queueform[K];
            for(int temp=0;temp<K;temp++)
                counterarray[temp]=new Queueform();	
        }
        else
            throw new IllegalNumberException("k is not good.");
    }   
    
    public void setM(int m) throws IllegalNumberException
    {
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        if(m>0)
        {
            M=m;
            garray=new Gnode[M];
            for(int i=0;i<M;i++)
                garray[i]=new Gnode();
        }
        else
            throw new IllegalNumberException("m is not good.");	
    } 



    public void griddlekakkam()
    {
        for(int temp=0;temp<M;temp++)
        {
            if(garray[temp].gendtime==pretime)
            {
                garray[temp].gstate=true;
            }
        }
        heapsegriddlemdalnewala();
    }

    public int freeM()
    {
        int freegriddle=0;
        for(int temp=0;temp<M;temp++)
        {
            if(garray[temp].gstate==true)
                freegriddle++;
        }
        return freegriddle;
    }


    public void heapsegriddlemdalnewala()
    {
        if(freeM()==0)
            return ;
        //System.out.println("Outside coming for updating departure time of "+xyz.array[1].id+" id");
        if(xyz.sizeofheap()>0)
        {
                addtogriddle();
                //System.out.println("Outside coming for updating departure time of "+xyz.array[1].id+" id");
                if(xyz.array[1].no==0)
                {
                    //System.out.println("coming for updating departure time of "+xyz.array[1].id+" id");
                    abc.carray[xyz.array[1].id-1].ct1=pretime+11;
                    xyz.removeheapnode(1);
                }
                heapsegriddlemdalnewala();
            
        }
        
       
    }

    public void addtogriddle()
    {
        
        for(int temp=0;temp<M;temp++)
        {
            if(garray[temp].gstate==true)
            {
                garray[temp].gstate=false;
                garray[temp].gendtime=pretime+10;
                xyz.array[1].no--;
                break;

            }
        }

    }

    public void advanceTime(int t) throws IllegalNumberException
    {
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        if(pretime==0)
        {
            while(preadded<preid)
            {
                int indexwheretoaddnewarrival=0;
                for(int temp=0;temp<K;temp++)
                {
                    if(counterarray[temp].sizeofqueue()<counterarray[indexwheretoaddnewarrival].sizeofqueue())
                        indexwheretoaddnewarrival=temp;
                }
                counterarray[indexwheretoaddnewarrival].enqueueform(abc.carray[preadded].cid,abc.carray[preadded].cno);
                if(counterarray[indexwheretoaddnewarrival].sizeofqueue()==0)
                    abc.carray[preadded].ctcountersefreehonewalatime=indexwheretoaddnewarrival+pretime+1;
                else
                    abc.carray[preadded].ctcountersefreehonewalatime=abc.carray[counterarray[indexwheretoaddnewarrival].lastqueue().id-1].ctcountersefreehonewalatime+indexwheretoaddnewarrival+1;;
                abc.carray[preadded].cstate=indexwheretoaddnewarrival+1;
                preadded++;
            }
            //pretime++;
        }
        if(pretime>t)
        	throw new IllegalNumberException("time is not good.");
        for(int temp=pretime+1;temp<=t;temp++)
        {
            pretime++;
            counterkakkam();
            griddlekakkam();
            customerupdate();//queue m add karne ke liye  or ye bhi aayegaa abc.carray[xyz.array[1].id-1].cstate=K+2;
        }
        pretime=t;
    } 




    public void customerupdate()
    {
        for(int i=0;i<preid;i++){
            int indexwheretoaddnewarrival=0;
            for(int temp=0;temp<K;temp++)
            {
                if(counterarray[temp].sizeofqueue()<counterarray[indexwheretoaddnewarrival].sizeofqueue())
                    indexwheretoaddnewarrival=temp;
            }
            if(abc.carray[i].ct0==pretime)
            {
                if(counterarray[indexwheretoaddnewarrival].sizeofqueue()==0)
                {
                    abc.carray[preadded].ctcountersefreehonewalatime=indexwheretoaddnewarrival+pretime+1;
                }
                else 
                {
                    abc.carray[i].ctcountersefreehonewalatime=abc.carray[counterarray[indexwheretoaddnewarrival].lastqueue().id-1].ctcountersefreehonewalatime+indexwheretoaddnewarrival+1+pretime;
                }
                counterarray[indexwheretoaddnewarrival].enqueueform(abc.carray[i].cid,abc.carray[i].cno);
                abc.carray[i].cstate=indexwheretoaddnewarrival+1;
            }
        }      
        for(int temp=0;temp<preid;temp++)
        {
            if(abc.carray[temp].ct1==pretime)
                abc.carray[temp].cstate=K+2;
        }
    }


    public void counterkakkam()
    {
        for(int temp=0;temp<K;temp++)
        {
            if(counterarray[temp].sizeofqueue()>0)
            {
                int idoffrontcustomer=counterarray[temp].topqueue().id;
                //System.out.println("id and free hone wala time"+idoffrontcustomer+"  "+abc.carray[idoffrontcustomer-1].ctcountersefreehonewalatime);
                if(abc.carray[idoffrontcustomer-1].ctcountersefreehonewalatime==pretime)
                {
                    int temp1=pretime-temp+1;
                    //System.out.println("id which is adding"+idoffrontcustomer+"priority"+temp1);
                    xyz.addheapnode(temp1,idoffrontcustomer,abc.carray[idoffrontcustomer-1].cno);
                    counterarray[temp].dequeueform();
                    abc.carray[idoffrontcustomer-1].cstate=K+1;
                }
            }
        }
    }

    public void arriveCustomer(int id, int t, int numb) throws IllegalNumberException
    {
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        if(preid+1==id && t>=pret && numb>0)
        {
            abc.addcustomer(id,t,numb);
            preid++;
            pret=t;
        }
        else
            throw new IllegalNumberException("id is not good.");
    } 

    public int customerState(int id, int t) throws IllegalNumberException
    {
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        if(t>=pretime)
        {
            advanceTime(t);	
            return abc.carray[id-1].cstate;
        }
        else
            throw new IllegalNumberException("time asked is not good.");
    } 

    public int griddleState(int t) throws IllegalNumberException
    {
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        //System.out.println("M="+M+" freem="+freeM());
        if(t>=pretime)
        {
            advanceTime(t); 

            return M-freeM();
        }
        else
            throw new IllegalNumberException("time asked is not good.");	
    } 

    public int griddleWait(int t) throws IllegalNumberException
    {
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        if(t>=pretime)
        {
            advanceTime(t); 
            return xyz.orderbachehue();
        }
        else
            throw new IllegalNumberException("time asked is not good.");
    } 

    public int customerWaitTime(int id) throws IllegalNumberException
    {
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        if(preid>=id)	
        {
            int temp=abc.carray[id-1].ct1-abc.carray[id-1].ct0;
            return temp;
        }
        else
            throw new IllegalNumberException("id asked is not good.");
    } 

	public float avgWaitTime()
    {
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
        float temp=0f;
        for(int index=0;index<preid;index++)
        {
            temp=temp+abc.carray[index].ct1-abc.carray[index].ct0;
        }	
        return temp/preid;
    } 

    
}









class Cnode
{
    public int cid;
    public int ct0;
    public int ct1;
    public int cstate;
    public int cno;
    public int ctcountersefreehonewalatime;
}



class Customerinfo
{
    public Cnode carray[]=new Cnode[1];
    public int Cn=1;
    public int Ci=0;
    public void addcustomer(int cid,int ct0,int cno)
    {
        if(Ci==Cn)
        {
            Cnode[] ca=new Cnode[2*Cn];
            for(int p=0;p<Cn;p++)
                ca[p]=carray[p];
            carray=ca;
            Cn=2*Cn;
        }
        carray[Ci]=new Cnode();
        carray[Ci].cid=cid;
        carray[Ci].ct0=ct0;
        carray[Ci].cno=cno;
        Ci++;
    }
}






class Gnode
{
    public boolean gstate=true;
    public int gendtime=0;
}




























class Heapsform
{
    public Node array[]=new Node[2];
    public int N=2;          // defined array size
    public int i=1;             //index where are we going t add now
    public void addheapnode(int prrty, int id, int no)
    {
        if(i==N)
        {
            Node[] a=new Node[2*N];
            for(int p=1;p<N;p++)
                a[p]=array[p];
            array=a;
            N=2*N;
        }
        array[i]=new Node();
        array[i].prrty=prrty;
        array[i].no=no;
        array[i].id=id;
        percolateup(prrty,i);
        i++;
    }
    public int sizeofheap()
    {
        return i-1;
    }
    public void percolateup(int prrty, int t)
    {
        if(t==1)
            return;
        if(t!=1)
        {
            if(array[t/2].prrty<array[t].prrty)
                return;
            if(array[t/2].prrty>array[t].prrty)
            {
                Node temp= new Node();
                temp=array[t/2];
                array[t/2]=array[t];
                array[t]=temp;
                percolateup(prrty,t/2);
            }
        }
    }
    public void removeheapnode(int k)
    {
        array[k]=array[i-1];
        int x=array[k].prrty;
        i--;
        percolatedown(x,k);

    }
    public void percolatedown(int prrty, int t)
    {
        if(2*t==i-1)
        {
            if(array[2*t].prrty>array[t].prrty)
                return;
            if(array[2*t].prrty<array[t].prrty)
            {
                Node temp=new Node();
                temp=array[t];
                array[t]=array[2*t];
                array[2*t]=temp;

            }
        }
        else if(2*t<i-1)
        {
            int j=0;
            if(array[2*t].prrty<array[2*t+1].prrty)
                j=2*t;
            else
                j=2*t+1;
            if(array[j].prrty>array[t].prrty)
                return;
            if(array[j].prrty<array[t].prrty)
            {
                Node temp=new Node();
                temp=array[t];
                array[t]=array[j];
                array[j]=temp;
                percolatedown(prrty,j);
            }
        }
    }
    public Node returnheapmin()
    {
        return array[1];
    }
    public void decreasekey(int k,int del)
    {
        array[k].prrty=array[k].prrty-del;
        percolateup(array[k].prrty,k);
    }
    public void increasekey(int k,int del)
    {
        array[k].prrty=array[k].prrty+del;
        percolatedown(array[k].prrty,k);
    }
    public int orderbachehue()
    {
        int order=0;
        for(int temp=1;temp<i;temp++)
            order=order+array[temp].no;
        return order;
    }
}













class Node
{
    public int prrty;
    public int no;
    public int id;
}







class Queueform
{
    public Node qarray[]=new Node[2];
    public int rear=0;
    public int front=0;
    public int N=2;
    public void enqueueform(int id,int no)
    {
        if((rear+1)%N==(front%N))
        {
            Node temp[]=new Node[2*N];
            int k=0;
            for(k=front;k<front+N-1;k++)
                temp[k]=qarray[k%N];
            qarray=temp;
            N=2*N;
            rear=k;
        }
        qarray[rear]=new Node();
        qarray[rear].id=id;
        qarray[rear].no=no;
        rear++;
    }
    public Node dequeueform()
    {
        Node x=new Node();
        x=qarray[front];
        front=(front+1)%N;
        return x;
    }
    public Node topqueue()
    {
        return qarray[front];
    }
    public Node lastqueue()
    {
        return qarray[rear-1];
    }
    public int sizeofqueue()
    {
        if(rear>=front)
            return (rear-front);
        else
            return (N+rear-front);
    }
}






















































