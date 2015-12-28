//By Sujata Regoti
//Date: 28 Dec 2014

import java.applet.Applet;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
/*<applet code="PingPongGame.class" width=550 height=550 ></applet>*/
public class  PingPongGame extends Applet implements KeyListener
{
	Timer timer;
	int b[]={100,75};
	String plyr="";
	int r=15;
	int vel[]={2,3};
	int refresh=17;
	int size=500;
	int padspeed=10;
	int ball_size=20;
	boolean gamever=false;
	int left=25,top=25;
	int top_pos=60,top1_pos=60;
	int right=left+size,bottom=top+size;
	int pad_wid=20,pad_ht=45;
	private Image i,back;
	private Graphics doubleB;
	int bond_left=left+pad_wid;
	int bond_right=right-pad_wid;
	public void init()
	{
		addKeyListener(this);
		this.requestFocus();
		setBackground(Color.yellow);
		back=getImage(getDocumentBase(),"bak.jpg");
		timer=new Timer();
		timer.schedule(new TimerTask()
		{
			public void run()
			{
				bounceBall();
			}
		},0,refresh);
	}
	void bounceBall()
	{
			if(b[0]<(left+r+pad_wid))
			{

				if(top_pos<=b[1]&&b[1]<=(top_pos+pad_ht))
				vel[0]=-vel[0];
				else{
					timer.cancel();
					gamever=true;
					plyr="Player 2 Won";
				}
				vel[0]=-vel[0];
			}

			if(b[0]>(right-ball_size-pad_wid)){
				
				if(top1_pos<=b[1]&&b[1]<=(top1_pos+pad_ht))
				vel[0]=-vel[0];
				else
				{
					timer.cancel();
					gamever=true;
					plyr="Player 1 Won";
				}
			}

			if(b[1]<top)
				vel[1]=-vel[1];
			if(b[1]>(bottom-ball_size))
				vel[1]=-vel[1];

			b[0]+=vel[0];
			b[1]+=vel[1];
			repaint();
	}
	public void update(Graphics g)
	{
		if(i==null)
		{
			i=createImage(getWidth(),getHeight());
			doubleB=i.getGraphics();
		}
		doubleB.setColor(getBackground());
		doubleB.fillRect(0,0,getWidth(),getHeight());
		doubleB.setColor(getForeground());
		paint(doubleB);
		g.drawImage(i,0,0,this);

	}
	public void paint(Graphics g)
	{
		if(!gamever){
		
			g.setFont(new Font("Tahoma",Font.BOLD,15));
			g.drawString("Player 1",left,15);
			g.drawString("Player 2",right-50,15);
			g.drawString("use left and right arrow keys",left,bottom+20);
			g.drawString("use 'W' and 'S' keys",right-150,bottom+20);
			g.drawImage(back,left,top,size,size,this);

			g.setColor(Color.black);
			g.drawRect(left,top,size,size);

			g.setColor(getBackground());
			g.drawLine(bond_left,top,bond_left,bottom);
			g.drawLine(bond_right,top,bond_right,bottom);
			
			g.setColor(Color.white);
			g.fillRect(left,top_pos,pad_wid,pad_ht);
			g.fillRect(right-pad_wid,top1_pos,pad_wid,pad_ht);

			g.setColor(Color.red);
			g.fillOval(b[0]-r,b[1]-r,2*r,2*r);

		}
		else
		{
			g.setFont(new Font("Tahoma",Font.BOLD,30));
			g.drawString(""+plyr+"!!!",getWidth()/2-150,getHeight()/2);
		}
	}

	public void keyPressed(KeyEvent ke){
		if(ke.getKeyCode()==ke.VK_DOWN)
		{
			if(top_pos+pad_ht<=bottom)
				top_pos+=padspeed;
		}
		if(ke.getKeyCode()==ke.VK_UP)
		{
			if(top_pos>=top)
				top_pos-=padspeed;
		}
		if(ke.getKeyCode()==ke.VK_W)
		{
			if(top1_pos>=top)
				top1_pos-=padspeed;
		}
		if(ke.getKeyCode()==ke.VK_S)
		{
			if(top1_pos<=bottom)
				top1_pos+=padspeed;
		}


		repaint();
	}
	public void keyReleased(KeyEvent ke){
		if(ke.getKeyCode()==ke.VK_DOWN)
		{
			if(top_pos+pad_ht<=bottom)
				top_pos+=padspeed;
		}
		if(ke.getKeyCode()==ke.VK_UP)
		{
			if(top_pos>=top)
				top_pos-=padspeed;
		}
		if(ke.getKeyCode()==ke.VK_W)
		{
			if(top1_pos>=top)
				top1_pos-=padspeed;
		}
		if(ke.getKeyCode()==ke.VK_S)
		{
			if(top1_pos<=bottom)
				top1_pos+=padspeed;
		}
		repaint();
	}
	public void keyTyped(KeyEvent ke){}

}
