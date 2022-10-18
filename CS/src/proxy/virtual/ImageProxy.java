package proxy.virtual;

import java.net.*;
import java.awt.*;
import javax.swing.*;

// Icon Interface는 java.swing에 있다.
public class ImageProxy implements Icon {
	
	// 이미지 로딩이 끝났을 경우 실제 이미지를 화면에 출력하는 진짜 아이콘 객체
	volatile ImageIcon imageIcon;
	final URL imageURL;
	Thread retrievalThread;
	boolean retrieving = false;

	// 이미지의 URL을 생성자에 전달
	// 로딩이 끝나면 URL에 있는 이미지를 화면에 출력
	public ImageProxy(URL url) {
		imageURL = url;
	}

	public int getIconWidth() {
		if (imageIcon != null) {
			return imageIcon.getIconWidth();
		} else {
			// imageIcon 로딩이 끝나기 전까지 기본 너비 리턴
			return 800;
		}
	}

	public int getIconHeight() {
		if (imageIcon != null) {
			return imageIcon.getIconHeight();
		} else {
			// imageIcon 로딩이 끝나기 전까지 기본 높이 리턴
			return 600;
		}
	}

	synchronized void setImageIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
	}

	// 아이콘을 화면에 표시할 때 호출하는 메소드
	public void paintIcon(final Component c, Graphics g, int x, int y) {
		if (imageIcon != null) {
			// 아이콘이 이미 준비되어 있다면 아이콘 객체의 메소드를 호출한다.
			imageIcon.paintIcon(c, g, x, y);
		} else {
			// 준비되어 있지 않다면 불로오고 있다는 메시지를 표시한다.
			g.drawString("앨범 커버를 불러오는 중입니다. 잠시만 기다려 주세요...", x + 300, y + 190);

			// 진짜 아이콘 이미지를 로딩하는 부분.
			// 이미지 로딩이 끝나기 전까지 ImageIcon 생성자에서 아무것도 리턴하지 않는다.
			
			// 이미지를 가져오고 있는 중이 아니라면
			if (!retrieving) {
				// 이미지 로딩 작업 시작.
				retrieving = true;

				retrievalThread = new Thread(new Runnable() {
					public void run() {
						try {
							// 이미지가 완전히 로딩되어야 생성자가 객체를 리턴.
							// 이미지가 확보되면 repaint()를 이용해 화면을 갱신한다.
							setImageIcon(new ImageIcon(imageURL, "CD Cover"));
							c.repaint();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				retrievalThread.start();
			}
		}
	}
}