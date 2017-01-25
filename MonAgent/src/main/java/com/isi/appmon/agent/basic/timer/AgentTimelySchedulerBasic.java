package com.isi.appmon.agent.basic.timer;

import com.isi.appmon.agent.Settings;
import com.isi.appmon.agent.basic.TCPTimelyUpdateBasic;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by samurdiw on 1/24/2017.
 */
public class AgentTimelySchedulerBasic extends TimerTask {
    private static AgentTimelySchedulerBasic agentTimelySchedulerBasic;

    public static AgentTimelySchedulerBasic getInstance(){
        if(agentTimelySchedulerBasic ==null){
            synchronized (AgentTimelySchedulerBasic.class){
                if(agentTimelySchedulerBasic ==null){
                    agentTimelySchedulerBasic =new AgentTimelySchedulerBasic();
                }
            }
        }
        return agentTimelySchedulerBasic;
    }

    private AgentTimelySchedulerBasic(){

    }

    public void run() {
        if(Settings.getProperty("AGENT.PUSH.METHOD").trim().equalsIgnoreCase("TCP")){
            invokeTCPTimelyUpdate();
        }
    }

    private void init(){
        Timer time = new Timer(); // Instantiate Timer Object
        time.schedule(this, 0, Long.parseLong(Settings.getProperty("TIMER.INTERVAL"))); // Create Repetitively task for every 1 secs   ,todo make it configurable
    }

    private void invokeTCPTimelyUpdate(){
        TCPTimelyUpdateBasic.getInstance().sendPeriodicUpdateMessage();
    }

    public static void main(String[] args) {
        AgentTimelySchedulerBasic agentTimelySchedulerBasic = AgentTimelySchedulerBasic.getInstance();
        agentTimelySchedulerBasic.init();
    }
}
