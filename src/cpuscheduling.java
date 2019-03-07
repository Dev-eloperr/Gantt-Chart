
/*
* @author : Dev Kathuria (1710110103)
* */

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

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

    private String[] proc = new String[]{"4", "2", "3", "1", "5", "6"};
    private JComboBox np = new JComboBox(proc);

    private Font fo = new Font("Serif", Font.BOLD, 14);
    private JTextField bt_t = new JTextField("10,4,5,3", 30);
    private JTextField at_t = new JTextField("0,1,2,3", 30);
    private JTextField p_t = new JTextField("1,2,5,4", 30);
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
    int i=0,total_selected=0;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    // the screen height
    double height = screenSize.getHeight();
    // the screen width
    double width = screenSize.getWidth();

    draw_graph(){

        //JPanel div = new JPanel(new GridLayout(6,2,0,0));
        JPanel div = new JPanel(new GridBagLayout());
        div.setBorder(BorderFactory.createLineBorder(Color.black));
        //div.setPreferredSize(new Dimension((int)height-50,(int)width));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.gridx=10;
        gbc.gridy=0;
        //div.add(new JLabel("."));
        div.add(new JTextField(30),gbc);
        //add(div);
        height-=50;
        System.out.println(height+"  "+width);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        total_selected=0;
        /*commented out line to find total selected checkboxes
        for(i=0;i<6;i++){
            if(chart.checkbox[i]==1)
                total_selected++;
        }
        */
        total_selected=6;
        int y=0;
        int[] wt_fcfs=new int[chart.n];
        int[] tat_fcfs=new int[chart.n];
        int[] service_time = new int[chart.n];
        int[] copy_service = new int[chart.n+1];
        for(i=0;i<total_selected;i++){
            Random rand = new Random();
            float rs = rand.nextFloat();
            float gs = rand.nextFloat();
            float bs = rand.nextFloat();
            g.setColor(Color.getHSBColor(rs,gs,bs));
            //g.fillRect(0,y,(int)width,(int)(height/total_selected));
            g.drawLine(0,y,(int)width,(int)y);
            y+=(height/total_selected);
        }
        g.drawLine((int)width/2,0,(int)width/2,(int)height);
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
                g.fillRect((int)(width*copy_service[i-1]/(2*sum)),0,(int)((width*copy_service[i]/(2*sum))-(width*copy_service[i-1]/(2*sum))),(int)((height/total_selected))-50);
                g.drawString("P"+i+":"+service_time[i-1],(int)(width*copy_service[i-1]/(2*sum)),(int)((height/total_selected)-25));
            }
            //Print of WT,AWT,TAT,ATAT
            g.setColor(Color.black);
            g.drawString("Average Waiting Time: ",(int)((width/2)+5),20);
            int x=0;
            for(i=0;i<wt_fcfs.length;i++) {
                x = i + 1;
                g.drawString("P" + x + ":" + wt_fcfs[i] + "  ", (int) (((width / 2)+50) + 100 * (i + 1)), 20);
            }
            g.drawString("Average WT: ",(int)((width/2)+5),40);
            double awt=0;
            for (i=0;i<wt_fcfs.length;i++){
                awt+=wt_fcfs[i];
            }
            System.out.println("awt is :"+awt);
            awt=awt/chart.n;
            g.drawString(awt+"",(int)((width/2)+150),40);


            g.drawString("Turn around Time: ",(int)((width/2)+5),60);
            for(i=0;i<wt_fcfs.length;i++) {
                x = i + 1;
                g.drawString("P" + x + ": " + tat_fcfs[i] + " ", (int) (((width / 2)+50) + 100 * (i + 1)), 60);
            }
            g.drawString("Average TAT: ",(int)((width/2)+5),80);
            double avg_tat_fcfs=0;
            for (i=0;i<tat_fcfs.length;i++){
                awt+=tat_fcfs[i];
            }
            System.out.println("avgtat is :"+avg_tat_fcfs);
            avg_tat_fcfs=avg_tat_fcfs/chart.n;
            g.drawString(avg_tat_fcfs+"",(int)((width/2)+150),80);


        }
        //FCFS selection ends here

        //Checking if Round Robin is selected
        if(chart.checkbox[1]==1){
            g.setColor(Color.black);
            g.drawString("Round Robin",2,(int)(2*(height/6)-10));
            ArrayList<Integer> seq = new ArrayList<Integer>();
            int[] ct_rr = new int[chart.n];
            int[] wt_rr = new int[chart.n];
            roundRobin(chart.at_arr,chart.bt_arr,chart.n,seq,ct_rr,wt_rr);
            for (i=0;i<ct_rr.length;i++)
                System.out.println("Comp rr: "+ct_rr[i]+" wait rr: "+wt_rr[i]);
            //for(i=0;i<seq.length();i++)
                System.out.println("Sequence is : "+seq);

            /*
            WaitTimeFCFS(chart.n,chart.bt_arr,chart.at_arr,wt_fcfs,service_time);
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
                g.fillRect((int)(width*copy_service[i-1]/(2*sum)),0,(int)((width*copy_service[i]/(2*sum))-(width*copy_service[i-1]/(2*sum))),(int)((height/total_selected))-50);
                g.drawString("P"+i+":"+service_time[i-1],(int)(width*copy_service[i-1]/(2*sum)),(int)((height/total_selected)-25));
            }
            */
            //Print of WT,AWT,TAT,ATAT
            g.setColor(Color.black);
            g.drawString("Average Waiting Time: ",(int)((width/2)+5),20+(int)(height/6));
            int x=0;
            for(i=0;i<wt_fcfs.length;i++) {
                x = i + 1;
                g.drawString("P" + x + ":" + wt_fcfs[i] + "  ", (int) (((width / 2)+50) + 100 * (i + 1)), 20+(int)(height/6));
            }
            g.drawString("Average WT: ",(int)((width/2)+5),40+(int)(height/6));
            double awt=0;
            for (i=0;i<wt_fcfs.length;i++){
                awt+=wt_fcfs[i];
            }
            System.out.println("awt is :"+awt);
            awt=awt/chart.n;
            g.drawString(awt+"",(int)((width/2)+150),40+(int)(height/6));


            g.drawString("Turn around Time: ",(int)((width/2)+5),60+(int)(height/6));
            for(i=0;i<wt_fcfs.length;i++) {
                x = i + 1;
                g.drawString("P" + x + ": " + wt_fcfs[i] + " ", (int) (((width / 2)+50) + 100 * (i + 1)), 60+(int)(height/6));
            }
            g.drawString("Average TAT: ",(int)((width/2)+5),80+(int)(height/6));
            double avg_tat_fcfs=0;
            for (i=0;i<tat_fcfs.length;i++){
                avg_tat_fcfs+=tat_fcfs[i];
            }
            System.out.println("avgtat is :"+avg_tat_fcfs);
            avg_tat_fcfs=avg_tat_fcfs/chart.n;
            g.drawString(avg_tat_fcfs+"",(int)((width/2)+150),80);

        }

    }
    //Paint Component ends over here
    //various function to calculate various components of different algorithms
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
    public static void roundRobin( int at_arr[], int bt_arr[], int n,ArrayList<Integer> seq,int ct_rr[], int wt_rr[]) {
        String[] p = new String[]{"1","2","3","4","5","6"};
        int copy_bt_arr[] = new int[n];
        int copy_at_arr[] = new int[n];
        int q=chart.timeQ;
        System.out.println(q);
        for (int x = 0; x < copy_bt_arr.length; x++) {
            copy_bt_arr[x] = bt_arr[x];
            copy_at_arr[x] = at_arr[x];
        }
        int t = 0;
        while (true) {
            boolean flag = true;
            for (int x = 0; x < n; x++) {
                if (copy_at_arr[x] <= t) {
                    if (copy_at_arr[x] <= q) {
                        if (copy_bt_arr[x] > 0) {
                            flag = false;
                            if (copy_bt_arr[x] > q) {
                                // make decrease the bt_arr time
                                t = t + q;
                                copy_bt_arr[x] = copy_bt_arr[x] - q;
                                copy_at_arr[x] = copy_at_arr[x] + q;
                                seq.add(Integer.parseInt(p[x]));
                            }
                            else {
                                // for last time
                                t = t + copy_bt_arr[x];
                                // store ct_rr time
                                ct_rr[x] = t - at_arr[x];
                                // store wait time
                                wt_rr[x] = ct_rr[x] - bt_arr[x];
                                copy_bt_arr[x] = 0;

                                // add sequence
                                seq.add(Integer.parseInt(p[x]));
                            }
                        }
                    }

                    /*
                    else if (copy_at_arr[x] > q) {
                        for (int j = 0; j < n; j++) {

                            if (copy_at_arr[j] < copy_at_arr[x]) {
                                if (copy_bt_arr[j] > 0) {
                                    flag = false;
                                    if (copy_bt_arr[j] > q) {
                                        t = t + q;
                                        copy_bt_arr[j] = copy_bt_arr[j] - q;
                                        copy_at_arr[j] = copy_at_arr[j] + q;
                                        seq.add(Integer.parseInt(p[j]));
                                    }
                                    else {
                                        t = t + copy_bt_arr[j];
                                        ct_rr[j] = t - at_arr[j];
                                        wt_rr[j] = t - bt_arr[j] - at_arr[j];
                                        copy_bt_arr[j] = 0;
                                        seq.add(Integer.parseInt(p[j]));
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
                                copy_bt_arr[x] = copy_bt_arr[x] - q;
                                copy_at_arr[x] = copy_at_arr[x] + q;
                                seq.add(Integer.parseInt(p[x]));
                            }
                            else {
                                t = t + copy_bt_arr[x];
                                ct_rr[x] = t - at_arr[x];
                                wt_rr[x] = t - bt_arr[x] - at_arr[x];
                                copy_bt_arr[x] = 0;
                                seq.add(Integer.parseInt(p[x]));
                            }
                        }
                    }
                    */
                }

                // if no process is come on thse critical
                else if (copy_at_arr[x] > t) {
                    t++;
                    x--;
                }
            }
            // for exit the while loop
            if (flag) {
                break;
            }
        }

    }

}