
/*
* @author : Dev Kathuria (1710110103)
* */

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

class cpuscheduling extends JFrame {
    private cpuscheduling(){
        chart c = new chart();
        add(c);
    }
    public static void main(String[] args) {
        try {

            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
        UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo look : looks) {
            System.out.println(look.getClassName());
        }
        cpuscheduling obj = new cpuscheduling();

        obj.setLayout(new FlowLayout());
        obj.setTitle("Input");
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.setSize(1000, 500);
        obj.setLocationRelativeTo(null);
    }
}
 class chart extends JPanel implements ActionListener {


    private JLabel numP = new JLabel("Number of Processes(<=6) : ");
    private JLabel priority = new JLabel("Priority : ");
    private JLabel tq = new JLabel("Time Quantam : ");

    private String[] proc = new String[]{"5", "2", "3", "1", "4", "6"};
    private JComboBox np = new JComboBox(proc);

    private Font fo = new Font("Serif", Font.BOLD, 14);
    private JTextField bt_t = new JTextField("6,2,8,3,4", 30);
    private JTextField at_t = new JTextField("2,5,1,0,4", 30);
    private JTextField p_t = new JTextField("3,1,4,2,4", 30);
    private JTextField tq_t = new JTextField("3",5);

    private JCheckBox fcfs = new JCheckBox("FCFS");
    private JCheckBox sjf = new JCheckBox("SJF");
    private JCheckBox rr = new JCheckBox("Round Robin");
    private JCheckBox sjfp = new JCheckBox("SJF_Preemptive");
    private JCheckBox pp = new JCheckBox("Priority_Preemptive");
    private JCheckBox npp = new JCheckBox("Priority_NonPreemptive");

    private String[] look = new String[]{"Metal", "Default"};
    private String[] win = new String[]{"pack", "Full Screen"};

    private JButton compute = new JButton("Compute");
    private JButton reset = new JButton("reset");


    private JMenu lf = new JMenu("Look and Feel");
    private JMenu ws = new JMenu("Window Size");

    public static int[] checkbox=new int[6];
     public static int[] bt_arr= new int[6];
     public static int[] at_arr= new int[6];
     public static int[] pt_arr= new int[6];
     public static int n=0,timeQ=0;

    public chart() {

        //Designing GUI

        //fcfs.setSelected(true);
        //sjf.setSelected(true);
        //rr.setSelected(true);
        sjfp.setSelected(true);
        //pp.setSelected(true);
        //npp.setSelected(true);

        bt_t.setFont(fo);
        at_t.setFont(fo);
        p_t.setFont(fo);
        tq_t.setFont(fo);

        bt_t.setForeground(Color.BLUE);
        at_t.setForeground(Color.BLUE);
        p_t.setForeground(Color.BLUE);
        tq_t.setForeground(Color.BLUE);

        JMenuBar formatbar = new JMenuBar();
        lf.add(look[0]);
        lf.add(look[1]);
        ws.add(win[0]);
        ws.add(win[1]);

        formatbar.add(lf);
        formatbar.add(ws);
        JPanel ptotal = new JPanel(new BorderLayout(5, 5));
        ptotal.add(formatbar, BorderLayout.NORTH);
        JPanel pmain = new JPanel(new GridLayout(11, 2, 5, 5));
        pmain.add(numP);
        pmain.add(np);
        JLabel bt = new JLabel("Burst Time : ");
        pmain.add(bt);
        pmain.add(bt_t);
        JLabel at = new JLabel("Arrival Time : ");
        pmain.add(at);
        pmain.add(at_t);
        pmain.add(priority);
        pmain.add(p_t);
        pmain.add(tq);
        pmain.add(tq_t);
        pmain.add(new JSeparator());
        pmain.add(new JSeparator());

        pmain.add(fcfs);
        pmain.add(rr);
        pmain.add(sjfp);
        pmain.add(sjf);
        pmain.add(pp);
        pmain.add(npp);
        pmain.add(compute);
        pmain.add(reset);

        pmain.setBorder(BorderFactory.createLineBorder(Color.black));
        ptotal.add(pmain, BorderLayout.CENTER);
        add(ptotal);
        //adding components ends here


        compute.addActionListener(this);
        reset.addActionListener(this);


        compute.setBackground(Color.white);
        reset.setBackground(Color.white);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (Objects.equals(e.getActionCommand(), "reset")) {
            System.out.println("reset");
            bt_t.setText("");
            at_t.setText("");
            tq_t.setText("");
            p_t.setText("");

        }
        else{
            //Extracting all values in arrays and variable

            n= Integer.parseInt(np.getSelectedItem().toString());
            System.out.println("Number of processes : "+n);

            //if(tq_t.getText().matches("^[0-9]*$")||!tq_t.getText().equals("")) {
                timeQ = Integer.parseInt(tq_t.getText());
            //}
            String[] temp;
            String array;
            array=bt_t.getText();
            temp = array.split("[,]");
            //if(bt_t.getText().matches("^[0-9]*$")) {
                for (int i = 0; i < temp.length; i++) {
                    bt_arr[i] = Integer.parseInt(temp[i]);
                }
            //}
            array=at_t.getText();
            temp = array.split("[,]");
            //if(at_t.getText().matches("^[0-9]*$")) {
            System.out.println(temp.length);
                for (int i = 0; i < temp.length; i++) {
                    at_arr[i] = Integer.parseInt(temp[i]);
                }
            //}
            array=p_t.getText();
            temp = array.split("[,]");
            //if(p_t.getText().matches("^[0-9]*$")) {
                for (int i = 0; i < temp.length; i++) {
                    pt_arr[i] = Integer.parseInt(temp[i]);
                }
            //}
            for (int i =0;i<n;i++)
                System.out.println("Arrival time "+at_arr[i]+" Burst time "+bt_arr[i]+" Priority "+pt_arr[i]);
            //extraction Complete

            if(fcfs.isSelected())
                checkbox[0]=1;
            else
                checkbox[0]=0;
            if(rr.isSelected())
                checkbox[1]=1;
            else
                checkbox[1]=0;
            if(sjfp.isSelected())
                checkbox[2]=1;
            else
                checkbox[2]=0;
            if(sjf.isSelected())
                checkbox[3]=1;
            else
                checkbox[3]=0;
            if(pp.isSelected())
                checkbox[4]=1;
            else
                checkbox[4]=0;
            if(npp.isSelected())
                checkbox[5]=1;
            else
                checkbox[5]=0;
            output_frame obj = new output_frame();
            obj.repaint();
        }
    }
}
class output_frame extends JFrame{
    private draw_graph obj = new draw_graph();
    output_frame(){
        add(obj);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Output");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
class draw_graph extends JPanel{
    int i=0;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    // the screen height
    double height = screenSize.getHeight();
    // the screen width
    double width = screenSize.getWidth();

    draw_graph(){
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y=0;
        int offset=50;
        int[] wt_fcfs=new int[chart.n];
        int[] tat_fcfs=new int[chart.n];
        int[] service_time = new int[chart.n];
        int[] copy_service = new int[chart.n+1];
        for(i=0;i<6;i++){
            Random rand = new Random();
            float rs = rand.nextFloat();
            float gs = rand.nextFloat();
            float bs = rand.nextFloat();
            g.setColor(Color.getHSBColor(rs,gs,bs));
            g.drawLine(0,y,(int)width,(int)y);
            y+=(height/6);
        }
        g.drawLine((int)width/2+50,0,(int)width/2+50,(int)height);
        //if FCFS is selected
        if(chart.checkbox[0]==1){
            g.setColor(Color.black);
            g.drawString("FCFS",2,(int)(height/6)-10);
            WaitTimeFCFS(chart.n,chart.bt_arr,chart.at_arr,wt_fcfs,service_time);
            TatFcfs(chart.n,chart.bt_arr,wt_fcfs,tat_fcfs);
            int sum=service_time[service_time.length-1]+chart.bt_arr[chart.n-1];
            System.out.println(sum);
            for(i=0;i<service_time.length;i++){
                copy_service[i]=service_time[i];
            }
            copy_service[i]=sum;
            for(i=1;i<copy_service.length;i++){
                Random rand = new Random();
                float rs = rand.nextFloat();
                float gs = rand.nextFloat();
                float bs = rand.nextFloat();
                g.setColor(Color.getHSBColor(rs,gs,bs));
                System.out.println((int)(width*copy_service[i]/(2*sum)));
                g.fillRect((int)(width*copy_service[i-1]/(2*sum)),0,(int)((width*copy_service[i]/(2*sum))-(width*copy_service[i-1]/(2*sum))),(int)((height/6))-50);
                g.setColor(Color.black);
                g.drawString("P"+i+":"+service_time[i-1],(int)(width*copy_service[i-1]/(2*sum)),(int)((height/6)-25));
            }
            g.setColor(Color.black);
            g.drawString(""+sum,(int)(width*copy_service[i-1]/(2*sum)),(int)((height/6)-25));
            //Print of WT,AWT,TAT,ATAT
            g.drawString("Waiting Time: ",(int)((width/2)+5)+offset,20);
            int x=0;
            for(i=0;i<wt_fcfs.length;i++) {
                x = i + 1;
                g.drawString("P" + x + ":" + wt_fcfs[i] + "  ", (int) (((width / 2)+50) + 100 * (i + 1))+offset, 20);
            }
            g.drawString("Average WT: ",(int)((width/2)+5)+offset,40);
            double awt=0;
            for (i=0;i<wt_fcfs.length;i++){
                awt+=wt_fcfs[i];
            }
            System.out.println("awt is :"+awt);
            awt=awt/chart.n;
            g.drawString(awt+"",(int)((width/2)+150)+offset,40);


            g.drawString("Turn around Time: ",(int)((width/2)+5)+offset,60);
            for(i=0;i<wt_fcfs.length;i++) {
                x = i + 1;
                g.drawString("P" + x + ": " + tat_fcfs[i] + " ", (int) (((width / 2)+50) + 100 * (i + 1))+offset, 60);
            }
            g.drawString("Average TAT: ",(int)((width/2)+5)+offset,80);
            double avg_tat_fcfs=0;
            for (i=0;i<tat_fcfs.length;i++){
                avg_tat_fcfs+=tat_fcfs[i];
            }
            System.out.println("avgtat is :"+avg_tat_fcfs);
            avg_tat_fcfs=avg_tat_fcfs/chart.n;
            g.drawString(avg_tat_fcfs+"",(int)((width/2)+150)+offset,80);


        }
        //FCFS selection ends here

        //Checking if Round Robin is selected
        if(chart.checkbox[1]==1){
            g.setColor(Color.black);
            g.drawString("Round Robin",2,(int)(2*(height/6)-10));
            ArrayList<Integer> seq = new ArrayList<>();
            int[] tat_rr = new int[chart.n];
            int[] wt_rr = new int[chart.n];
            int n= chart.n;
            int sum_bt=0;
            for (i=0;i<chart.bt_arr.length;i++)
                sum_bt+=chart.bt_arr[i];
            String[] p = new String[]{"1","2","3","4","5","6"};
            int copy_bt_arr[] = new int[n];
            int copy_at_arr[] = new int[n];
            int q=chart.timeQ;
            int widq=(int)(width/(2*sum_bt))*q;
            int widt=(int)(width/(2*sum_bt))*q;
            //System.out.println(widq);
            int var_x=0;
            System.out.println(q);
            for (int x = 0; x < copy_bt_arr.length; x++) {
                copy_bt_arr[x] = chart.bt_arr[x];
                copy_at_arr[x] = chart.at_arr[x];
            }
            int t = 0;
            ArrayList<Integer> ct_rr= new ArrayList<>();
            boolean flag = false;
            while (flag==false) {
                flag = true;
                for (int x = 0; x < n; x++) {
                    if (copy_at_arr[x] <= t) {
                        if (copy_at_arr[x] <= q) {
                            if (copy_bt_arr[x] > 0) {
                                flag = false;
                                if (copy_bt_arr[x] > q) {
                                    // make decrease the bt_arr time
                                    t = t + q;
                                    System.out.println(" t: "+t);
                                    ct_rr.add(t);
                                    copy_bt_arr[x] = copy_bt_arr[x] - q;
                                    copy_at_arr[x] = copy_at_arr[x] + q;
                                    seq.add(Integer.parseInt(p[x]));
                                    //g.drawString(seq.get(seq.size()-1),x,y);
                                    //var_x=t*widq/q;
                                    //g.fillRect(var_x,(int)(height/6),widq,(int)(height/6)-50);
                                }
                                else {
                                    // for last time
                                    t = t + copy_bt_arr[x];
                                    System.out.println(" t: "+t);
                                    ct_rr.add(t);
                                    // store tat_rr time
                                    tat_rr[x] = t - chart.at_arr[x];
                                    // store wait time
                                    wt_rr[x] = tat_rr[x] - chart.bt_arr[x];
                                    //copy_bt_arr[x] = 0;

                                    // add sequence
                                    seq.add(Integer.parseInt(p[x]));
                                    //widt=(int)(width/(2*sum_bt))*(copy_bt_arr[x]);
                                    //var_x=t*widt/(copy_bt_arr[x]);
                                    copy_bt_arr[x] = 0;
                                    //g.fillRect(var_x,(int)height/6,widt,(int)height/6-50);
                                }
                            }
                        }


                        else if (copy_at_arr[x] > q) {
                            for (int j = 0; j < n; j++) {

                                if (copy_at_arr[j] < copy_at_arr[x]) {
                                    if (copy_bt_arr[j] > 0) {
                                        flag = false;
                                        if (copy_bt_arr[j] > q) {
                                            t = t + q;
                                            System.out.println(" t: "+t);
                                            ct_rr.add(t);
                                            copy_bt_arr[j] = copy_bt_arr[j] - q;
                                            copy_at_arr[j] = copy_at_arr[j] + q;
                                            seq.add(Integer.parseInt(p[j]));

                                            //widt=(int)(width/(2*sum_bt))*q;
                                            //var_x=t*widt/q;
                                            //g.fillRect(var_x,(int)height/6,widt,(int)height/6-50);
                                        }
                                        else {
                                            t = t + copy_bt_arr[j];
                                            System.out.println(" t: "+t);
                                            ct_rr.add(t);
                                            tat_rr[j] = t - chart.at_arr[j];
                                            wt_rr[j] = t - chart.bt_arr[j] - chart.at_arr[j];
                                            //copy_bt_arr[j] = 0;
                                            seq.add(Integer.parseInt(p[j]));

                                            //widt=(int)(width/(2*sum_bt))*(copy_bt_arr[j]);
                                            //var_x=t*widt/(copy_bt_arr[j]);
                                            copy_bt_arr[j] = 0;
                                            //g.fillRect(var_x,(int)height/6,widt,(int)height/6-50);
                                        }
                                    }
                                }
                            }

                            // now the previous process according to
                            // ith is process
                            if (copy_bt_arr[x] > 0) {
                                flag = false;

                                // Check for greaters
                                if (copy_bt_arr[x] > q) {
                                    t = t + q;
                                    System.out.println(" t: "+t);
                                    ct_rr.add(t);
                                    copy_bt_arr[x] = copy_bt_arr[x] - q;
                                    copy_at_arr[x] = copy_at_arr[x] + q;
                                    seq.add(Integer.parseInt(p[x]));
                                    //var_x=t*widq/q;
                                    //g.fillRect(var_x,(int)(height/6),widq,(int)(height/6)-50);
                                }
                                else {
                                    t = t + copy_bt_arr[x];
                                    System.out.println(" t: "+t);
                                    ct_rr.add(t);
                                    tat_rr[x] = t - chart.at_arr[x];
                                    wt_rr[x] = t - chart.bt_arr[x] - chart.at_arr[x];
                                    //copy_bt_arr[x] = 0;
                                    seq.add(Integer.parseInt(p[x]));
                                    //widt=(int)(width/(2*sum_bt))*(copy_bt_arr[x]);
                                    //var_x=t*widt/(copy_bt_arr[x]);
                                    copy_bt_arr[x] = 0;
                                    //g.fillRect(var_x,(int)height/6,widt,(int)height/6-50);
                                }
                            }
                        }

                    }

                    // if no process is come on thse critical
                    else if (copy_at_arr[x] > t) {
                        t++;
                        x--;
                    }
                }
                /** for exit the while loop
                if (flag) {
                    break;
                }
                 */
            }
            int sum=ct_rr.get(ct_rr.size()-1);
            for(i=0;i<ct_rr.size();i++) {
                Random rand = new Random();
                float rs = rand.nextFloat();
                float gs = rand.nextFloat();
                float bs = rand.nextFloat();
                if(i==0) {
                    g.setColor(Color.getHSBColor(rs, gs, bs));
                    System.out.println("Width for 0th : "+(int)(((width * ct_rr.get(i) / (2 * sum)) - (width * chart.at_arr[i] / (2 * sum)))));
                    g.fillRect(0, (int)height/6, (int) ((width * ct_rr.get(i) / (2 * sum)) - (width * chart.at_arr[i] / (2 * sum))), (int) ((height / 6)) - 50);
                    g.setColor(Color.black);
                    g.drawString(" " + chart.at_arr[i], 0, (int) (2*(height / 6) - 25));
                    g.drawString("P" + seq.get(i) + " " + ct_rr.get(i), (int) ((width * ct_rr.get(i) / (2 * sum)) - (width * chart.at_arr[i] / (2 * sum))), (int) (2 * (height / 6) - 25));
                }
                else {
                    g.setColor(Color.getHSBColor(rs, gs, bs));
                    System.out.println("x_coor: "+(int)(width * ct_rr.get(i-1) / (2 * sum))+"width : "+(int) ((width * ct_rr.get(i) / (2 * sum)) - (width * ct_rr.get(i - 1) / (2 * sum))));
                    g.fillRect((int) (width * ct_rr.get(i-1) / (2 * sum)), (int)height/6, (int) ((width * ct_rr.get(i) / (2 * sum)) - (width * ct_rr.get(i - 1) / (2 * sum))), (int) ((height / 6)) - 50);
                    g.setColor(Color.black);
                    if(i<ct_rr.size()-1) {
                        g.drawString("P" + seq.get(i) + " " + ct_rr.get(i), (int) (width * ct_rr.get(i) / (2 * sum)), (int) (2 * (height / 6) - 25));
                    }
                    else
                        g.drawString(""+ct_rr.get(i), (int) (width * ct_rr.get(i) / (2 * sum)), (int) (2*(height / 6) - 25));
                }
            }
            //Round Robin Graph Printing ends here
            for (i=0;i<tat_rr.length;i++)
                System.out.println("tat rr: "+tat_rr[i]+" wait rr: "+wt_rr[i]);
            System.out.println("Sequence is : "+seq);
            //Print of WT,AWT,TAT,ATAT
            g.setColor(Color.black);
            g.drawString("Waiting Time: ",(int)((width/2)+5)+offset,20+(int)(height/6));
            int x=0;
            for(i=0;i<wt_rr.length;i++) {
                x = i + 1;
                g.drawString("P" + x + ":" + wt_rr[i] + "  ", (int) (((width / 2)+50) + 100 * (i + 1))+offset, 20+(int)(height/6));
            }
            g.drawString("Average WT: ",(int)((width/2)+5)+offset,40+(int)(height/6));
            double awt=0;
            for (i=0;i<wt_rr.length;i++){
                awt+=wt_rr[i];
            }
            awt=awt/chart.n;
            g.drawString(awt+"",(int)((width/2)+150)+offset,40+(int)(height/6));

            g.drawString("Turn around Time: ",(int)((width/2)+5)+offset,60+(int)(height/6));
            for(i=0;i<tat_rr.length;i++) {
                x = i + 1;
                g.drawString("P" + x + ": " + tat_rr[i] + " ", (int) (((width / 2)+50) + 100 * (i + 1))+offset, 60+(int)(height/6));
            }
            g.drawString("Average TAT: ",(int)((width/2)+5)+offset,80+(int)(height/6));
            double avg_tat_rr=0;
            for (i=0;i<tat_fcfs.length;i++){
                avg_tat_rr+=tat_rr[i];
            }
            avg_tat_rr=avg_tat_rr/chart.n;
            g.drawString(avg_tat_rr+"",(int)((width/2)+150)+offset,80+(int)(height/6));
        }
        //SJF NON PREEMPTIVE
        if(chart.checkbox[3]==1){
            g.setColor(Color.black);
            g.drawString("SJF NonPreemptive",2,(int)(4*(height/6)-10));
            int[] s_bt_arr=new int[chart.n];
            int[] s_at_arr=new int[chart.n];
            int[] wt_sjf=new int[chart.n];
            int[] ser_sjf=new int[chart.n];
            int[] tat_sjf=new int[chart.n];
            int swap_b,swap_a;
            String swap_c = new String();

            ArrayList<String> p = new ArrayList<>();
            for(i=0;i<chart.n;i++) {
                s_bt_arr[i]=chart.bt_arr[i];
                s_at_arr[i]=chart.at_arr[i];
                System.out.println(s_at_arr[i]+" "+s_bt_arr[i]+" "+chart.bt_arr[i]+" "+chart.at_arr[i]);
                p.add("P" + (i + 1));
            }
            System.out.println("NNNNNNNNNNN IS "+chart.n);
            for(i=0;i<chart.n;i++){
                for(int j=0;j<chart.n-i-1;j++){
                    if(s_bt_arr[j]>s_bt_arr[j+1]){
                        swap_b = s_bt_arr[j];
                        swap_a = s_at_arr[j];
                        swap_c = p.get(j);

                        s_bt_arr[j]=s_bt_arr[j+1];
                        s_at_arr[j]=s_at_arr[j+1];
                        p.set(j,p.get(j+1));

                        s_bt_arr[j+1] = swap_b;
                        s_at_arr[j+1] = swap_a;
                        p.set(j+1,swap_c);

                    }
                }
            }
            //check weather all of them are sorted nicely
            for (i=0;i<chart.n;i++){
                System.out.println("bt : "+s_bt_arr[i]+"  at : "+s_at_arr[i]+"  process : "+p.get(i));
            }
            //now that burst time has been sorted we can use fcfs logic to implement non preemptive sjf
            WaitTimeFCFS(chart.n,s_bt_arr,s_at_arr,wt_sjf,ser_sjf);
            TatFcfs(chart.n,s_bt_arr,wt_sjf,tat_sjf);
            for (i=0;i<tat_sjf.length;i++)
                System.out.println("tat_sjf: "+tat_sjf[i]+"Wait sjf: "+wt_sjf[i]+" process: "+p.get(i));
            //Graph drawing logic
            int height_offset=3*((int)height/6);
            int sum=ser_sjf[ser_sjf.length-1]+s_bt_arr[chart.n-1];
            System.out.println(sum);
            for(i=0;i<ser_sjf.length;i++){
                copy_service[i]=ser_sjf[i];
            }
            copy_service[i]=sum;
            for(i=1;i<copy_service.length;i++){
                Random rand = new Random();
                float rs = rand.nextFloat();
                float gs = rand.nextFloat();
                float bs = rand.nextFloat();
                g.setColor(Color.getHSBColor(rs,gs,bs));
                System.out.println((int)(width*copy_service[i]/(2*sum)));
                g.fillRect((int)(width*copy_service[i-1]/(2*sum)),height_offset,(int)((width*copy_service[i]/(2*sum))-(width*copy_service[i-1]/(2*sum))),(int)((height/6))-50);
                g.setColor(Color.black);
                g.drawString(p.get(i-1)+":"+ser_sjf[i-1],(int)(width*copy_service[i-1]/(2*sum)),(int)(4*(height/6)-25));
            }
            g.setColor(Color.black);
            g.drawString(""+sum,(int)(width*copy_service[i-1]/(2*sum)),(int)(4*(height/6)-25));
            //Print of WT,AWT,TAT,ATAT
            g.drawString("Waiting Time: ",(int)((width/2)+5)+offset,20+height_offset);
            int x=0;
            for(i=0;i<wt_sjf.length;i++) {
                x = i + 1;
                g.drawString(p.get(i)+":" + wt_sjf[i] + "  ", (int) (((width / 2)+50) + 100 * (i + 1))+offset, 20+height_offset);
            }
            g.drawString("Average WT: ",(int)((width/2)+5)+offset,40+height_offset);
            double awt=0;
            for (i=0;i<wt_sjf.length;i++){
                awt+=wt_sjf[i];
            }
            System.out.println("awt is :"+awt);
            awt=awt/chart.n;
            g.drawString(awt+"",(int)((width/2)+150)+offset,40+height_offset);


            g.drawString("Turn around Time: ",(int)((width/2)+5)+offset,60+height_offset);
            for(i=0;i<wt_sjf.length;i++) {
                g.drawString(p.get(i) +":"+ tat_sjf[i] + " ", (int) (((width / 2)+50) + 100 * (i + 1))+offset, 60+height_offset);
            }
            g.drawString("Average TAT: ",(int)((width/2)+5)+offset,80+height_offset);
            double avg_tat_sjf=0;
            for (i=0;i<tat_sjf.length;i++){
                avg_tat_sjf+=tat_sjf[i];
            }
            System.out.println("avgtat is :"+avg_tat_sjf);
            avg_tat_sjf=avg_tat_sjf/chart.n;
            g.drawString(avg_tat_sjf+"",(int)((width/2)+150)+offset,80+height_offset);

        }//End of if for sjf_non preemptive
        //check if non primitive priority is selected
        if(chart.checkbox[5]==1){
            g.setColor(Color.black);
            g.drawString("Priority NonPreemptive",2,(int)(6*(height/6)-30));
            int[] s_bt_arr=new int[chart.n];
            int[] s_at_arr=new int[chart.n];
            int[] wt_p=new int[chart.n];
            int[] ser_p=new int[chart.n];
            int[] tat_p=new int[chart.n];
            int[] pri = new int[chart.n];
            int swap_b,swap_a,swap_d;
            String swap_c = new String();

            ArrayList<String> p = new ArrayList<>();
            for(i=0;i<chart.n;i++) {
                pri[i]= chart.pt_arr[i];
                s_bt_arr[i]=chart.bt_arr[i];
                s_at_arr[i]=chart.at_arr[i];
                System.out.println(s_at_arr[i]+" "+s_bt_arr[i]+" "+chart.bt_arr[i]+" "+chart.at_arr[i]);
                p.add("P" + (i + 1));
            }
            System.out.println(s_bt_arr[0]);
            for(i=0;i<chart.n;i++){
                for(int j=0;j<chart.n-i-1;j++){
                    if(pri[j]>pri[j+1]){
                        swap_b = s_bt_arr[j];
                        swap_a = s_at_arr[j];
                        swap_c = p.get(j);
                        swap_d = pri[j];

                        s_bt_arr[j]=s_bt_arr[j+1];
                        s_at_arr[j]=s_at_arr[j+1];
                        p.set(j,p.get(j+1));
                        pri[j]=pri[j+1];

                        s_bt_arr[j+1] = swap_b;
                        s_at_arr[j+1] = swap_a;
                        pri[j+1] = swap_d;
                        p.set(j+1,swap_c);

                    }
                }
            }
            //check weather all of them are sorted nicely
            for (i=0;i<chart.n;i++){
                System.out.println("bt : "+s_bt_arr[i]+"  at : "+s_at_arr[i]+"  process : "+p.get(i)+" priority: "+pri[i]);
            }
            //now that burst time has been sorted we can use fcfs logic to implement non preemptive sjf
            WaitTimeFCFS(chart.n,s_bt_arr,s_at_arr,wt_p,ser_p);
            TatFcfs(chart.n,s_bt_arr,wt_p,tat_p);

            //Graph drawing logic
            int height_offset=5*((int)height/6);

            int sum=ser_p[ser_p.length-1]+s_bt_arr[chart.n-1];
            System.out.println(sum);
            for(i=0;i<ser_p.length;i++){
                copy_service[i]=ser_p[i];
            }
            copy_service[i]=sum;
            for(i=1;i<copy_service.length;i++){
                Random rand = new Random();
                float rs = rand.nextFloat();
                float gs = rand.nextFloat();
                float bs = rand.nextFloat();
                g.setColor(Color.getHSBColor(rs,gs,bs));
                System.out.println((int)(width*copy_service[i]/(2*sum)));
                g.fillRect((int)(width*copy_service[i-1]/(2*sum)),height_offset,(int)((width*copy_service[i]/(2*sum))-(width*copy_service[i-1]/(2*sum))),(int)((height/6))-50);
                g.setColor(Color.black);
                g.drawString(p.get(i-1)+":"+ser_p[i-1],(int)(width*copy_service[i-1]/(2*sum)),(int)(6*(height/6)-40));
            }
            g.setColor(Color.black);
            g.drawString(""+sum,(int)(width*copy_service[i-1]/(2*sum)),(int)(6*(height/6)-40));
            //Print of WT,AWT,TAT,ATAT
            g.drawString("Waiting Time: ",(int)((width/2)+5)+offset,20+height_offset);
            int x=0;
            for(i=0;i<wt_p.length;i++) {
                x = i + 1;
                g.drawString(p.get(i)+":" + wt_p[i] + "  ", (int) (((width / 2)+50) + 100 * (i + 1))+offset, 20+height_offset);
            }
            g.drawString("Average WT: ",(int)((width/2)+5)+offset,40+height_offset);
            double awt=0;
            for (i=0;i<wt_p.length;i++){
                awt+=wt_p[i];
            }
            System.out.println("awt is :"+awt);
            awt=awt/chart.n;
            g.drawString(awt+"",(int)((width/2)+150)+offset,40+height_offset);


            g.drawString("Turn around Time: ",(int)((width/2)+5)+offset,60+height_offset);
            for(i=0;i<wt_p.length;i++) {
                g.drawString(p.get(i) +":"+ tat_p[i] + " ", (int) (((width / 2)+50) + 100 * (i + 1))+offset, 60+height_offset);
            }
            g.drawString("Average TAT: ",(int)((width/2)+5)+offset,80+height_offset);
            double avg_tat_p=0;
            for (i=0;i<tat_p.length;i++){
                avg_tat_p+=tat_p[i];
            }
            System.out.println("avgtat is :"+avg_tat_p);
            avg_tat_p=avg_tat_p/chart.n;
            g.drawString(avg_tat_p+"",(int)((width/2)+150)+offset,80+height_offset);

        }//End of if for priority non preemptive

        //check if sjf preemptive is selected
        if(chart.checkbox[2]==1){
            g.setColor(Color.black);
            g.drawString("SJF Preemptive",2,(int)(3*(height/6)-10));
            ArrayList<Integer> pr = new ArrayList<>();
            int[] seq_time=new int[50]; //to get the sequence of preemptiveness
            int[] wt = new int[chart.n]; //Waiting time;
            preemptiveRR(wt,seq_time,pr);

            for(int i=0;seq_time[i]!=0;i++)
                System.out.println(" Time of execution: "+seq_time[i]);
            System.out.println("Order of execution: "+pr);

            //printing graph starts here
            int sum_bt=0,sum_ft=0;
            for(i=0;i<chart.bt_arr.length;i++){
                sum_bt+=chart.bt_arr[i];
            }
            System.out.println("Sum_bt is: "+sum_bt);
            for(i=0;i<pr.size();i++){
                Random rand = new Random();
                float rs = rand.nextFloat();
                float gs = rand.nextFloat();
                float bs = rand.nextFloat();
                g.setColor(Color.getHSBColor(rs,gs,bs));
                System.out.println((int)width*sum_ft/(2*sum_bt));
                g.fillRect((int)width*sum_ft/(2*sum_bt),(int)height/3,(int)((width*seq_time[i])/(2*sum_bt)),(int)((height/6))-50);
                sum_ft+=seq_time[i];
                System.out.println("Sum_ft is: "+sum_ft);
                g.setColor(Color.black);
                g.drawString("P"+pr.get(i)+" "+sum_ft,(int)((width*(sum_ft))/(2*sum_bt)),(int)(3*(height/6)-25));
            }
            g.setColor(Color.black);
            //g.drawString(""+sum_bt,(int)((width*seq_time[i-1])/(2*sum_bt)),(int)(3*(height/6)-25));
            //Print of WT,AWT,TAT,ATAT
            g.drawString("Waiting Time: ",(int)((width/2)+5)+offset,20+(int)height/3);
            int x=0;
            for(i=0;i<wt.length;i++) {
                x = i + 1;
                g.drawString("P" + x + ":" + wt[i] + "  ", (int) (((width / 2)+50) + 100 * (i + 1))+offset, 20+(int)height/3);
            }
            g.drawString("Average WT: ",(int)((width/2)+5)+offset,40+(int)height/3);
            double awt=0;
            for (i=0;i<wt.length;i++){
                awt+=wt[i];
            }
            System.out.println("awt is :"+awt);
            awt=awt/chart.n;
            g.drawString(awt+"",(int)((width/2)+150)+offset,40+(int)height/3);

            //function to calculate TAT
            int[] tat_sjfp = new int[chart.n];
            for (int i = 0; i < chart.n; i++)
                tat_sjfp[i] = chart.bt_arr[i] + wt[i];

            g.drawString("Turn around Time: ",(int)((width/2)+5)+offset,60+(int)height/3);
            for(i=0;i<tat_sjfp.length;i++) {
                x = i + 1;
                g.drawString("P" + x + ": " + tat_sjfp[i] + " ", (int) (((width / 2)+50) + 100 * (i + 1))+offset, 60+(int)height/3);
            }
            g.drawString("Average TAT: ",(int)((width/2)+5)+offset,80+(int)height/3);
            double avg_tat_sjfp=0;
            for (i=0;i<tat_sjfp.length;i++){
                avg_tat_sjfp+=tat_sjfp[i];
            }
            System.out.println("avgtat is :"+avg_tat_sjfp);
            avg_tat_sjfp=avg_tat_sjfp/chart.n;
            g.drawString(avg_tat_sjfp+"",(int)((width/2)+150)+offset,80+(int)height/3);

        }
        //end of sjf preemptive

        //Check if preemptive priority is selected
        if(chart.checkbox[4]==1){
            g.setColor(Color.black);
            g.drawString("Priority Preemptive",2,(int)(5*(height/6)-10));
            int[] wt = new int[chart.n]; //Waiting time;
            ArrayList<Integer> pr = new ArrayList<>();
            int[] seq_time=new int[50]; //to get the sequence of preemptiveness
            preemptivePriority(wt,seq_time,pr);

            for(int i=0;seq_time[i]!=0;i++)
                System.out.println(" Time of execution: "+seq_time[i]);
            System.out.println("Order of execution: "+pr);

            //printing graph starts here
            int sum_bt,sum_ft;
            sum_bt=0;
            sum_ft=0;
            for(i=0;i<chart.bt_arr.length;i++){
                sum_bt+=chart.bt_arr[i];
            }
            for(i=0;i<pr.size();i++){
                Random rand = new Random();
                float rs = rand.nextFloat();
                float gs = rand.nextFloat();
                float bs = rand.nextFloat();
                g.setColor(Color.getHSBColor(rs,gs,bs));
                g.fillRect((int)width*sum_ft/(2*sum_bt),(int)(height*4)/6,(int)((width*seq_time[i])/(2*sum_bt)),(int)((height/6))-50);
                sum_ft+=seq_time[i];
                g.setColor(Color.black);
                g.drawString("P"+pr.get(i)+" "+sum_ft,(int)((width*(sum_ft))/(2*sum_bt)),(int)(5*(height/6)-25));
            }
            g.setColor(Color.black);
            //g.drawString(""+sum_bt,(int)((width*seq_time[i-1])/(2*sum_bt)),(int)(3*(height/6)-25));
            //Print of WT,AWT,TAT,ATAT
            g.drawString("Waiting Time: ",(int)((width/2)+5)+offset,20+(int)(height*4)/6);
            int x=0;
            for(i=0;i<wt.length;i++) {
                x = i + 1;
                g.drawString("P" + x + ":" + wt[i] + "  ", (int) (((width / 2)+50) + 100 * (i + 1))+offset, 20+(int)(height*4)/6);
            }
            g.drawString("Average WT: ",(int)((width/2)+5)+offset,40+(int)(height*4)/6);
            double awt=0;
            for (i=0;i<wt.length;i++){
                awt+=wt[i];
            }
            System.out.println("awt is :"+awt);
            awt=awt/chart.n;
            g.drawString(awt+"",(int)((width/2)+150)+offset,40+(int)(height*4)/6);

            //function to calculate TAT
            int[] tat_sjfp = new int[chart.n];
            for (int i = 0; i < chart.n; i++)
                tat_sjfp[i] = chart.bt_arr[i] + wt[i];

            g.drawString("Turn around Time: ",(int)((width/2)+5)+offset,60+(int)(height*4)/6);
            for(i=0;i<tat_sjfp.length;i++) {
                x = i + 1;
                g.drawString("P" + x + ": " + tat_sjfp[i] + " ", (int) (((width / 2)+50) + 100 * (i + 1))+offset, 60+(int)(height*4)/6);
            }
            g.drawString("Average TAT: ",(int)((width/2)+5)+offset,80+(int)(height*4)/6);
            double avg_tat_sjfp=0;
            for (i=0;i<tat_sjfp.length;i++){
                avg_tat_sjfp+=tat_sjfp[i];
            }
            System.out.println("avgtat is :"+avg_tat_sjfp);
            avg_tat_sjfp=avg_tat_sjfp/chart.n;
            g.drawString(avg_tat_sjfp+"",(int)((width/2)+150)+offset,80+(int)(height*4)/6);

            sum_bt=0;
            sum_ft=0;
        }
        //end of preemptive priority
    }
    //Paint Component ends over here
    //various function to calculate various components of different algorithms
    void preemptiveRR(int[] wt,int[] seq_time,ArrayList<Integer> pr){
        int[] flag = new int[chart.n]; //checks if all are complete
        int minValue= Integer.MAX_VALUE;
        int short_index = 0; //To keep track of the process with the shortest burst time at that point of time
        int[] comp_time = new int[chart.n]; //completion Time

        int ifFound=0;//checks if process with shortest job has been found
        int flag_multiplier=0; //checks if all have completed

        int k=0;//to keep track of sequence array

        int[] copy_bt_arr = new int[chart.n]; //copy of orignal burst times
        int t=0; // time
        int[] processes = new int[]{1,2,3,4,5,6};

        for(i=0;i<chart.n;i++){
            copy_bt_arr[i]=chart.bt_arr[i];
        }
        while(flag_multiplier!=1) {
            for (i = 0; i < chart.n; i++) {
                //find shortest job among the jobs in ready queue
                if ((chart.at_arr[i] <= t) && (copy_bt_arr[i] < minValue) && (copy_bt_arr[i] > 0)) {
                    minValue = copy_bt_arr[i];
                    short_index = i;
                    ifFound = 1;
                }
            }

            if (ifFound == 0) {
                t++;
                continue;//increase the time quantam and skip to next iteration
            }
            copy_bt_arr[short_index]--;//decrease by one time quantam
            if (pr.size() != 0) {
                if (pr.get(pr.size() - 1) != processes[short_index]) {
                    pr.add(processes[short_index]);
                    k++;
                    seq_time[k]++;
                } else {
                    seq_time[k]++;

                }

            } else {
                pr.add(processes[short_index]);
                seq_time[k]++;
            }

            minValue = copy_bt_arr[short_index];
            if (minValue == 0) {
                minValue = Integer.MAX_VALUE;
            }
            if (copy_bt_arr[short_index] == 0) {
                flag[short_index] = 1;
                ifFound = 0;
                comp_time[short_index] = t + 1;
                wt[short_index] = comp_time[short_index] - chart.bt_arr[short_index
                        ] - chart.at_arr[short_index];
                if (wt[short_index] < 0)
                    wt[short_index] = 0;
            }
            t++;


            //Function to calculate flag multiplier
            flag_multiplier = 1;
            for (i = 0; i < flag.length; i++)
                flag_multiplier *= flag[i];
        }
    }

    void preemptivePriority(int[] wt,int[] seq_time,ArrayList<Integer> pr){
        int[] flag = new int[chart.n]; //checks if all are complete
        int minValue= Integer.MAX_VALUE;
        int short_index = 0; //To keep track of the process with the shortest burst time at that point of time
        int[] comp_time = new int[chart.n]; //completion Time

        int ifFound=0;//checks if process with shortest job has been found
        int flag_multiplier=0; //checks if all have completed

        //ArrayList<Integer> seq_time = new ArrayList<>();

        int k=0;//to keep track of sequence array
        int[] pri = new int[chart.n];
        int[] copy_bt_arr = new int[chart.n]; //copy of orignal burst times
        int t=0; // time
        int[] processes = new int[]{1,2,3,4,5,6};

        for(i=0;i<chart.n;i++){
            copy_bt_arr[i]=chart.bt_arr[i];
            pri[i]=chart.pt_arr[i];
        }
        while(flag_multiplier!=1) {
            for (i = 0; i < chart.n; i++) {
                //find shortest job among the jobs in ready queue
                if ((chart.at_arr[i] <= t) && (pri[i] < minValue) && (copy_bt_arr[i] > 0) && (flag[i]==0)) {
                    minValue = pri[i];
                    short_index = i;
                    ifFound = 1;
                }
            }

            if (ifFound == 0) {
                t++;
                continue;//increase the time quantam and skip to next iteration
            }
            copy_bt_arr[short_index]--;//decrease by one time quantam
            if (pr.size() != 0) {
                if (pr.get(pr.size() - 1) != processes[short_index]) {
                    pr.add(processes[short_index]);
                    k++;
                    seq_time[k]++;
                } else {
                    seq_time[k]++;

                }

            } else {
                pr.add(processes[short_index]);
                seq_time[k]++;
            }

            minValue = Integer.MAX_VALUE;
            if (copy_bt_arr[short_index] == 0) {
                flag[short_index] = 1;
                ifFound = 0;
                comp_time[short_index] = t + 1;
                wt[short_index] = comp_time[short_index] - chart.bt_arr[short_index
                        ] - chart.at_arr[short_index];
                if (wt[short_index] < 0)
                    wt[short_index] = 0;
            }
            t++;


            //Function to calculate flag multiplier
            flag_multiplier = 1;
            for (i = 0; i < flag.length; i++)
                flag_multiplier *= flag[i];
        }
    t=0;
    }

    void WaitTimeFCFS(int n, int bt_arr[], int at_arr[], int wt_fcfs[],int service_time[])
    {
        //service_time[] = new int[n];
        service_time[0] = 0;
        wt_fcfs[0] = 0;
        for (int i = 1; i < n ; i++) {
            service_time[i] = service_time[i-1] + bt_arr[i-1];
            wt_fcfs[i] = service_time[i] - at_arr[i];
            if (wt_fcfs[i] < 0)
                wt_fcfs[i] = 0;
        }
        for(i=0;i<service_time.length;i++){
            System.out.println(bt_arr[i]+"  "+service_time[i]+"  "+wt_fcfs[i]+"  ");
        }
    }

    // Function to calculate turn around time
    static void TatFcfs(int n, int bt_arr[],int wt_fcfs[], int tat_fcfs[]) {
        for (int i = 0; i < n ; i++)
            tat_fcfs[i] = bt_arr[i] + wt_fcfs[i];
    }

}