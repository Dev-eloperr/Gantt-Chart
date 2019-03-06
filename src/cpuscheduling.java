
/*
* @author : Dev Kathuria (1710110103)
* */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class cpuscheduling extends JFrame{
    chart c = new chart();
     int flag=2, i=0, res=0;
     ArrayList<Integer> list = new ArrayList<Integer>();
    String[] temp = new String[100];
    public cpuscheduling() {
        add(c);
    }
    public static void main(String[] args) {
        cpuscheduling obj = new cpuscheduling();
        obj.setLayout(new FlowLayout());
        obj.setTitle("Input");
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.setSize(600,600);
        obj.setLocationRelativeTo(null);
        //obj.setBackground(Color.white); obj = new GanttChart();

    }
    public class chart extends JPanel implements ActionListener {


        JLabel numP = new JLabel("Number of Processes(<=6) : ");
        JLabel bt = new JLabel("Burst Time : ");
        JLabel at = new JLabel("Arrival Time : ");
        JLabel priority = new JLabel("Priority : ");
        JLabel tq = new JLabel("Time Quantam : ");

        String[] proc = new String[]{"P1","P2","P3","P4","P5","P6"};
        JComboBox np = new JComboBox(proc);

        Font fo = new Font("Serif", Font.BOLD, 18);
        JTextField bt_t = new JTextField("Enter Burst Time",30);
        JTextField at_t = new JTextField("Enter Arrival Time",30);
        JTextField p_t = new JTextField("(Lower Number,Higher Priority(<=10)",30);
        JTextField tq_t = new JTextField(5);

        JCheckBox fcfs = new JCheckBox("FCFS");
        JCheckBox sjf = new JCheckBox("SJF");
        JCheckBox rr = new JCheckBox("Round Robin");
        JCheckBox sjfp = new JCheckBox("SJF_Preemptive");
        JCheckBox Pp = new JCheckBox("Priority_Preemptive");
        JCheckBox npp = new JCheckBox("Priority_NonPreemptive");

        JButton compute = new JButton("Compute");

        String array = new String();

        JButton reset = new JButton("reset");

        JTextField input = new JTextField(30);

        displaychart obj =new displaychart();
        public chart() {
            bt_t.setFont(fo);
            at_t.setFont(fo);
            p_t.setFont(fo);
            JPanel pmain = new JPanel()



            compute.addActionListener(this);
            reset.addActionListener(this);
            fcfs.setBackground(Color.white);
            sjf.setBackground(Color.white);
            compute.setBackground(Color.white);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            array = null;
            array = input.getText();
            temp=null;
            temp = array.split("[,;.]");
            res=1;
            list.removeAll(list);
            for(i=1;i<temp.length;i=i+2){
                list.add(Integer.parseInt(temp[i]));
            }
            if((fcfs.isSelected())&&(sjf.isSelected())){
                flag=3;
                obj.repaint();

            }
            else if(sjf.isSelected())
            {
                Collections.sort(list);
                flag=0;
                obj.repaint();
            }
            else if(fcfs.isSelected()){
                flag=1;
                obj.repaint();
            }

            if(e.getActionCommand()=="reset"){
                System.out.println("reset");
                input.setText("");
                res=0;
                obj.repaint();
            }

            /*
            for(;i<array.length();i++){
                if((array.charAt(i)==',' )){
                    i=i+1;
                    System.out.println(i);
                    while(((array.charAt(i)!=';')||(array.charAt(i)!='.'))){
                        temp=""+array.charAt(i);
                        i=i+1;
                    }
                    list.add(Integer.parseInt(temp));
                    temp=null;
                }


            }
            System.out.print(Arrays.toString(temp));
            System.out.println(list);
            */
        }
        /*
        @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.white);
            }
*/
    }
    class displaychart extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(res==0){
                setBackground(Color.white);
                setBorder( BorderFactory.createEtchedBorder());
                setPreferredSize(new Dimension(400,200));
            }
            else{
                int sum;
                sum=0;
                for(i=0;i<list.size();i++){
                    sum=sum+list.get(i);
                }
                int x=0,x1=0,j,y1=200,xs1=0,xs=0;
                if(flag==3){
                    y1=y1/2;
                }
                System.out.println(list);

                for(i=0,j=0;i<list.size();i++,j=j+2){
                    Random rand = new Random();
                    float rs = rand.nextFloat();
                    float gs = rand.nextFloat();
                    float bs = rand.nextFloat();
                    System.out.println(" i = "+list.get(i));
                    x =list.get(i);
                    x=x*400/sum;
                    System.out.println(x);
                    g.setColor(new Color(rs, gs, bs));
                    g.fillRect(x1, 0, x, y1);

                    if((flag==1) || (flag==3)){
                        System.out.println(temp[j]);
                        g.setColor(Color.black);
                        g.drawString(temp[j], x1+(x/2), y1/2);
                    }
                    else if(flag==0){
                        for(j=1;j<temp.length;j=j+2){
                            if(list.get(i) == Integer.parseInt(temp[j])){
                                System.out.println(temp[j-1]);
                                g.setColor(Color.black);
                                g.drawString(temp[j-1], x1+(x/2), y1/2);

                                System.err.println(list);
                                break;
                            }
                        }
                        temp[j]="-1";
                    }

                    x1+=x;
                }

                if(flag==3){

                    g.setColor(Color.black);
                    g.drawString("FCFS", 15, 15);


                    g.drawLine(0, y1, 400, y1);
                    Collections.sort(list);
                    for(i=0;i<list.size();i++){
                        Random rand = new Random();
                        float rs = rand.nextFloat();
                        float gs = rand.nextFloat();
                        float bs = rand.nextFloat();



                        g.setColor(new Color(rs, gs, bs));
                        xs =list.get(i);

                        xs=xs*400/sum;
                        g.fillRect(xs1, y1+1, xs, y1-1);
                        xs1+=xs;
                        for(j=1;j<temp.length;j=j+2){
                            if(list.get(i) == Integer.parseInt(temp[j])){
                                System.out.println(temp[j-1]);
                                g.setColor(Color.black);
                                g.drawString(temp[j-1], xs1-(xs/2), y1+y1/2);
                                //list.set(j, -1);
                                //list.remove(j-1);


                                System.err.println(list);
                                break;
                            }
                        }
                        temp[j]="-1";
                        g.setColor(Color.black);
                        g.drawString("SJF", 15, y1+15);
                    }

                }

            }
        }
    }
}
