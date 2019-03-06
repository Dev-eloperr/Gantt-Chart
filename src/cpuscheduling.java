
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

public class cpuscheduling extends JFrame {
    chart c = new chart();
    int flag = 2, i = 0, res = 0;
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
        obj.setSize(1000, 500);
        obj.setLocationRelativeTo(null);
        //obj.setBackground(Color.white); obj = new GanttChart();

    }

    public class chart extends JPanel implements ActionListener {


        JLabel numP = new JLabel("Number of Processes(<=6) : ");
        JLabel bt = new JLabel("Burst Time : ");
        JLabel at = new JLabel("Arrival Time : ");
        JLabel priority = new JLabel("Priority : ");
        JLabel tq = new JLabel("Time Quantam : ");

        String[] proc = new String[]{"1", "2", "3", "4", "5", "6"};
        JComboBox np = new JComboBox(proc);

        Font fo = new Font("Serif", Font.BOLD, 14);
        JTextField bt_t = new JTextField("Enter Burst Time", 30);
        JTextField at_t = new JTextField("Enter Arrival Time", 30);
        JTextField p_t = new JTextField("(Lower Number,Higher Priority(<=10)", 30);
        JTextField tq_t = new JTextField(5);

        JCheckBox fcfs = new JCheckBox("FCFS");
        JCheckBox sjf = new JCheckBox("SJF");
        JCheckBox rr = new JCheckBox("Round Robin");
        JCheckBox sjfp = new JCheckBox("SJF_Preemptive");
        JCheckBox pp = new JCheckBox("Priority_Preemptive");
        JCheckBox npp = new JCheckBox("Priority_NonPreemptive");

        String[] look = new String[]{"Metal", "Default"};
        String[] win = new String[]{"pack", "Full Screen"};

        JButton compute = new JButton("Compute");
        JButton reset = new JButton("reset");


        JMenu lf = new JMenu("Look and Feel");
        JMenu ws = new JMenu("Window Size");

        displaychart obj = new displaychart();


        String array = new String();
        JTextField input = new JTextField(30);


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
            pmain.add(bt);
            pmain.add(bt_t);
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
            array = null;
            array = input.getText();
            temp = null;
            temp = array.split("[,;.]");
            res = 1;
            list.removeAll(list);
            for (i = 1; i < temp.length; i = i + 2) {
                list.add(Integer.parseInt(temp[i]));
            }
            if ((fcfs.isSelected()) && (sjf.isSelected())) {
                flag = 3;
                obj.repaint();

            } else if (sjf.isSelected()) {
                Collections.sort(list);
                flag = 0;
                obj.repaint();
            } else if (fcfs.isSelected()) {
                flag = 1;
                obj.repaint();
            }

            if (e.getActionCommand() == "reset") {
                System.out.println("reset");
                bt_t.setText("");
                at_t.setText("");
                tq_t.setText("");
                p_t.setText("");

                res = 0;
                obj.repaint();
            }
        }

        class displaychart extends JPanel {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (res == 0) {
                    setBackground(Color.white);
                    setBorder(BorderFactory.createEtchedBorder());
                    setPreferredSize(new Dimension(400, 200));
                } else {
                    int sum;
                    sum = 0;
                    for (i = 0; i < list.size(); i++) {
                        sum = sum + list.get(i);
                    }
                    int x = 0, x1 = 0, j, y1 = 200, xs1 = 0, xs = 0;
                    if (flag == 3) {
                        y1 = y1 / 2;
                    }
                    System.out.println(list);

                    for (i = 0, j = 0; i < list.size(); i++, j = j + 2) {
                        Random rand = new Random();
                        float rs = rand.nextFloat();
                        float gs = rand.nextFloat();
                        float bs = rand.nextFloat();
                        System.out.println(" i = " + list.get(i));
                        x = list.get(i);
                        x = x * 400 / sum;
                        System.out.println(x);
                        g.setColor(new Color(rs, gs, bs));
                        g.fillRect(x1, 0, x, y1);

                        if ((flag == 1) || (flag == 3)) {
                            System.out.println(temp[j]);
                            g.setColor(Color.black);
                            g.drawString(temp[j], x1 + (x / 2), y1 / 2);
                        } else if (flag == 0) {
                            for (j = 1; j < temp.length; j = j + 2) {
                                if (list.get(i) == Integer.parseInt(temp[j])) {
                                    System.out.println(temp[j - 1]);
                                    g.setColor(Color.black);
                                    g.drawString(temp[j - 1], x1 + (x / 2), y1 / 2);

                                    System.err.println(list);
                                    break;
                                }
                            }
                            temp[j] = "-1";
                        }

                        x1 += x;
                    }

                    if (flag == 3) {

                        g.setColor(Color.black);
                        g.drawString("FCFS", 15, 15);


                        g.drawLine(0, y1, 400, y1);
                        Collections.sort(list);
                        for (i = 0; i < list.size(); i++) {
                            Random rand = new Random();
                            float rs = rand.nextFloat();
                            float gs = rand.nextFloat();
                            float bs = rand.nextFloat();


                            g.setColor(new Color(rs, gs, bs));
                            xs = list.get(i);

                            xs = xs * 400 / sum;
                            g.fillRect(xs1, y1 + 1, xs, y1 - 1);
                            xs1 += xs;
                            for (j = 1; j < temp.length; j = j + 2) {
                                if (list.get(i) == Integer.parseInt(temp[j])) {
                                    System.out.println(temp[j - 1]);
                                    g.setColor(Color.black);
                                    g.drawString(temp[j - 1], xs1 - (xs / 2), y1 + y1 / 2);
                                    //list.set(j, -1);
                                    //list.remove(j-1);


                                    System.err.println(list);
                                    break;
                                }
                            }
                            temp[j] = "-1";
                            g.setColor(Color.black);
                            g.drawString("SJF", 15, y1 + 15);
                        }

                    }

                }
            }
        }
    }
}