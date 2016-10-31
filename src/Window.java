import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

//����ֽ����Ϸ�Ĵ���
public class Window {
	// ����
	private JFrame window;
	// ����Ϊÿһ����ӽ�ȥ��������ò�ε����
	private JLayeredPane panel;
	// ������Ϊ�յı�־
	private JLabel no_card;
	// ��������
	private JLabel[] RP_card = new JLabel[13];
	// ��������
	private JLabel[] BP_card = new JLabel[13];
	// ��Ƭ����
	private JLabel[] RD_card = new JLabel[13];
	// ÷������
	private JLabel[] BF_card = new JLabel[13];
	// ����Ƶĺ������꣬�����趨�Ƶ�λ��
	private static int pressX, pressY;
	// �������ļ����������ڹ���������
	private static int count = 0;
	

	// discard�İ�ť������
	private class Listener1 implements ActionListener {

		// ÿ�ε����ť�ͻ�һ����
		public void actionPerformed(ActionEvent e) {
			// countΪ0ʱ������discard��ʼλ�ã���ʼ����һ����
			if (count == 0)
				panel.remove(no_card);

			// ֻҪcountС��discard�ĳ��ȾͿ��Լ������·��ƣ�ͨ���ڲ��洢���������ҵ�����������ݵ�label
			if (count < InsideData.discard.length()) {
				Card tempcard = InsideData.discard.get(count);
				String kind = tempcard.getKind();
				int number = tempcard.getNumber();
				int x = tempcard.getX();
				int y = tempcard.getY();
				if (kind.equals("RP")) {
					RP_card[number - 1].setBounds(x, y, 150, 200);
					panel.add(RP_card[number - 1], (Integer) (count + 1));
					tempcard.setLayer(count + 1);
				} else if (kind.equals("RD")) {
					RD_card[number - 1].setBounds(x, y, 150, 200);
					panel.add(RD_card[number - 1], (Integer) (count + 1));
					tempcard.setLayer(count + 1);
				} else if (kind.equals("BF")) {
					BF_card[number - 1].setBounds(x, y, 150, 200);
					panel.add(BF_card[number - 1], (Integer) (count + 1));
					tempcard.setLayer(count + 1);
				} else {
					BP_card[number - 1].setBounds(x, y, 150, 200);
					panel.add(BP_card[number - 1], (Integer) (count + 1));
					tempcard.setLayer(count + 1);
				}
				count++;
			}
			// count����discard�ĳ���˵������discard��ĩ�ˣ�Ҫ������д��ڵ�����ͷ���ƣ���ʱ������ʾ��־
			else {
				no_card.setBounds(190, 20, 150, 200);
				panel.add(no_card, (Integer) 25);
				for (int i = 0; i < count; i++) {
					Card tempcard = InsideData.discard.get(i);
					String kind = tempcard.getKind();
					int number = tempcard.getNumber();
					if (kind.equals("RP")) {
						panel.remove(RP_card[number - 1]);
						tempcard.setLayer(0);
					} else if (kind.equals("RD")) {
						panel.remove(RD_card[number - 1]);
						tempcard.setLayer(0);
					} else if (kind.equals("BF")) {
						panel.remove(BF_card[number - 1]);
						tempcard.setLayer(0);
					} else {
						panel.remove(BP_card[number - 1]);
						tempcard.setLayer(0);
					}
				}
				count = 0;

			}

			panel.updateUI();
		}

	}

	// �����Ƶ���갴ѹ���ɿ�������
	private class MouseAdapter1 extends MouseAdapter {

		// ��갴ѹ�󣬿��Ե�֪��ѹ��һ����
		// ��������Ϣ���ݸ���ק��������ֽ�Ƶ��ƶ�����ԭʼ����Ͳ����Ϣ���ݸ��ͷż�������ֽ���޷��ƶ��ص�ԭλ�ã�ͬʱ�������ֽ�Ʒŵ����ϲ�
		public void mousePressed(MouseEvent e) {
			pressX = e.getX();
			pressY = e.getY();
			JLabel l = (JLabel) e.getSource();
			String str = l.getIcon().toString();
			String kind = str.substring(0, 2);
			int number = Integer.parseInt(str.substring(2, 4));
			Card nowcard;
			if (kind.equals("RP"))
				nowcard = InsideData.getcard.get(number - 1);
			else if (kind.equals("RD"))
				nowcard = InsideData.getcard.get(number - 1 + 13);
			else if (kind.equals("BP"))
				nowcard = InsideData.getcard.get(number - 1 + 26);
			else
				nowcard = InsideData.getcard.get(number - 1 + 39);

			// ѡ�е�ֽ����tablepile��
			if (nowcard.getY() >= 300) { // �õ����ĸ�tablepile��
				int j;
				if (nowcard.getX() == 20)
					j = 0;
				else if (nowcard.getX() == 190)
					j = 1;
				else if (nowcard.getX() == 360)
					j = 2;
				else if (nowcard.getX() == 530)
					j = 3;
				else if (nowcard.getX() == 700)
					j = 4;
				else if (nowcard.getX() == 870)
					j = 5;
				else
					j = 6;

				// Ҫ�ƶ�ֽ�Ƶ�����
				int numberOfMoveCard = number
						- InsideData.tablepile[j].getTopCardNumber() + 1;

				// ѡ��ֽ���ڵڼ���
				int nowIndex = InsideData.tablepile[j].length()
						- numberOfMoveCard;

				// ��Ҫ�ƶ���ֽ�Ʒŵ�������
				for (int i = 0; i < numberOfMoveCard; i++) {
					Card card = InsideData.tablepile[j].getCard(nowIndex + i);
					String nowIndexCardKind = card.getKind();
					int nowIndexCardNumber = card.getNumber();

					JLabel nowIndexCard;
					if (nowIndexCardKind.equals("RP")) {
						nowIndexCard = RP_card[nowIndexCardNumber - 1];
					} else if (nowIndexCardKind.equals("RD")) {
						nowIndexCard = RD_card[nowIndexCardNumber - 1];
					} else if (nowIndexCardKind.equals("BF")) {
						nowIndexCard = BF_card[nowIndexCardNumber - 1];
					} else {
						nowIndexCard = BP_card[nowIndexCardNumber - 1];
					}

					panel.remove(nowIndexCard);
					panel.add(nowIndexCard, (Integer) (60 + i));
				}
			}
			// ѡ�е�ֽ����discard����suitpile��
			else {
				panel.remove(l);
				panel.add(l, (Integer) 60);
			}
		}

		public void mouseReleased(MouseEvent e) {
			// �õ�ѡ��ֽ�Ƶ�ͼ����Ϣ��������Ϣ
			JLabel l = (JLabel) e.getSource();
			String str = l.getIcon().toString();
			String kind = str.substring(0, 2);
			int number = Integer.parseInt(str.substring(2, 4));
			Card nowcard;
			if (kind.equals("RP"))
				nowcard = InsideData.getcard.get(number - 1);
			else if (kind.equals("RD"))
				nowcard = InsideData.getcard.get(number - 1 + 13);
			else if (kind.equals("BP"))
				nowcard = InsideData.getcard.get(number - 1 + 26);
			else
				nowcard = InsideData.getcard.get(number - 1 + 39);

			// ������ͷ�ʱѡ��ֽ�����Ͻǵ�����
			int nowX = l.getX() + e.getX() - pressX;
			int nowY = l.getY() + e.getY() - pressY;
			// ������ͷ�ʱѡ��ֽ�����ĵ�����
			int nowCenterX = nowX + 75;
			int nowCenterY = nowY + 100;

			// ��discard����suitpileȡ����
			if (nowcard.getY() < 250) {
				// ��discardȡ����
				if (nowcard.getX() < 350) {
					// �ŵ�suitpile��
					if (nowCenterY > 20 && nowCenterY < 220) {
						boolean ismove = false;
						// �ŵ���һ��suitpile��
						for (int i = 0; i < 4 && !ismove; i++) {
							// ��������ʺϣ����Է���
							if (nowCenterX > 530 + i * 170
									&& nowCenterX < 680 + i * 170)
								// �����ɫ�͵������������
								if (InsideData.suitpile[i].isSuitable(nowcard)) {
									InsideData.discard.delete(count - 1);
									count--;
									InsideData.suitpile[i].add(nowcard);
									nowcard.setX(530 + i * 170);
									nowcard.setY(20);
									nowcard.setLayer(InsideData.suitpile[i]
											.length());
									panel.remove(l);
									panel.add(l,
											(Integer) InsideData.suitpile[i]
													.length());
									// �Ѿ����룬����ѭ��
									ismove = true;

									// �жϣ���suitpile��������Ӯ�ˣ�������ʾ��Ϣ
									if (InsideData.suitpile[0].length() == 13
											&& InsideData.suitpile[1].length() == 13
											&& InsideData.suitpile[2].length() == 13
											&& InsideData.suitpile[3].length() == 13)
										JOptionPane.showMessageDialog(null,
												"You win!");
								}
						}
						// ���벻�ɹ�������ԭλ��
						if (!ismove) {
							panel.remove(l);
							panel.add(l, (Integer) nowcard.getLayer());
						}
					}

					// �ŵ�tablepile��
					else {
						if (nowCenterY > 300) {
							boolean ismove = false;
							// �ŵ���һ��tablepile��
							for (int i = 0; i < 7 && !ismove; i++) {
								// ���������ʳ��Է���
								if (nowCenterX > 20 + 170 * i
										&& nowCenterX < 170 + 170 * i)
									// �����ɫ�͵������������
									if (InsideData.tablepile[i]
											.isSuitable(nowcard)) {
										InsideData.discard.delete(count - 1);
										count--;
										InsideData.tablepile[i].add(nowcard);
										nowcard.setX(20 + 170 * i);
										nowcard.setY(300 + 20 * (InsideData.tablepile[i]
												.length() - 1));
										nowcard.setLayer(InsideData.tablepile[i]
												.length());
										panel.remove(l);
										panel.add(
												l,
												(Integer) InsideData.tablepile[i]
														.length());
										// ����ɹ�����ѭ��
										ismove = true;
									}

							}
							// ���벻�ɹ�����ԭλ��
							if (!ismove) {
								panel.remove(l);
								panel.add(l, (Integer) nowcard.getLayer());
							}

						}
						// ���겻���ʣ�����ԭλ��
						else {
							panel.remove(l);
							panel.add(l, (Integer) nowcard.getLayer());

						}
					}

				}

				// ��suitpileȡ����
				else {
					// ����һ��suitpile��ȡ����
					int j;
					if (nowcard.getX() == 530)
						j = 0;
					else if (nowcard.getX() == 700)
						j = 1;
					else if (nowcard.getX() == 870)
						j = 2;
					else
						j = 3;

					// �ŵ�suitpile��
					if (nowCenterY > 20 && nowCenterY < 220) {
						boolean ismove = false;
						// �ŵ���һ��suitpile��
						for (int i = 0; i < 4 && !ismove; i++) {
							// �������������Է���
							if (nowCenterX > 530 + i * 170
									&& nowCenterX < 680 + i * 170)
								// �����ɫ�͵������������
								if (InsideData.suitpile[i].isSuitable(nowcard)) {
									InsideData.suitpile[j].delete();
									InsideData.suitpile[i].add(nowcard);
									nowcard.setX(530 + i * 170);
									nowcard.setY(20);
									nowcard.setLayer(InsideData.suitpile[i]
											.length());
									panel.remove(l);
									panel.add(l,
											(Integer) InsideData.suitpile[i]
													.length());
									// ����ɹ�������ѭ��
									ismove = true;
								}
						}
						// ���벻�ɹ�����ԭλ��
						if (!ismove) {
							panel.remove(l);
							panel.add(l, (Integer) nowcard.getLayer());
						}
					}

					// �ŵ�tablepile��
					else {
						if (nowCenterY > 300) {
							boolean ismove = false;
							// �ŵ���һ��tablepile��
							for (int i = 0; i < 7 && !ismove; i++) {
								// �������������Է���
								if (nowCenterX > 20 + 170 * i
										&& nowCenterX < 170 + 170 * i)
									// �����ɫ�������������
									if (InsideData.tablepile[i]
											.isSuitable(nowcard)) {
										InsideData.suitpile[j].delete();
										InsideData.tablepile[i].add(nowcard);
										nowcard.setX(20 + 170 * i);
										nowcard.setY(300 + 20 * (InsideData.tablepile[i]
												.length() - 1));
										nowcard.setLayer(InsideData.tablepile[i]
												.length());
										panel.remove(l);
										panel.add(
												l,
												(Integer) InsideData.tablepile[i]
														.length());
										// ����ɹ�������ѭ��
										ismove = true;
									}
							}

							// ���벻�ɹ�������ԭλ��
							if (!ismove) {
								panel.remove(l);
								panel.add(l, (Integer) nowcard.getLayer());
							}
						}
						// �������겻���ʷ���ԭλ��
						else {
							panel.remove(l);
							panel.add(l, (Integer) nowcard.getLayer());
						}
					}
				}
				l.setLocation(nowcard.getX(), nowcard.getY());// ��������ʾͻص�ԭ��
			}

			// ��tablepileȡ����
			else {
				// ����һ��tablepileȡ����
				int j;
				if (nowcard.getX() == 20)
					j = 0;
				else if (nowcard.getX() == 190)
					j = 1;
				else if (nowcard.getX() == 360)
					j = 2;
				else if (nowcard.getX() == 530)
					j = 3;
				else if (nowcard.getX() == 700)
					j = 4;
				else if (nowcard.getX() == 870)
					j = 5;
				else
					j = 6;
				// �ƶ�һ����
				if (number == InsideData.tablepile[j].getTopCardNumber()) {
					// �ŵ�suitpile��
					if (nowCenterY > 20 && nowCenterY < 220) {
						boolean ismove = false;
						// �ŵ���һ��suitpile��
						for (int i = 0; i < 4 && !ismove; i++) {
							// �������������Է���
							if (nowCenterX > 530 + i * 170
									&& nowCenterX < 680 + i * 170)
								// �����ɫ�͵������ʣ������
								if (InsideData.suitpile[i].isSuitable(nowcard)) {
									InsideData.tablepile[j].delete();
									InsideData.suitpile[i].add(nowcard);
									nowcard.setX(530 + i * 170);
									nowcard.setY(20);
									nowcard.setLayer(InsideData.suitpile[i]
											.length());
									panel.remove(l);
									panel.add(l,
											(Integer) InsideData.suitpile[i]
													.length());
									// ����ɹ�������ѭ��
									ismove = true;

									// �ж�suitpile�Ƿ����������������Ӯ��
									if (InsideData.suitpile[0].length() == 13
											&& InsideData.suitpile[1].length() == 13
											&& InsideData.suitpile[2].length() == 13
											&& InsideData.suitpile[3].length() == 13)
										JOptionPane.showMessageDialog(null,
												"You win!");
								}
						}
						// �ƶ����ɹ�������ԭλ��
						if (!ismove) {
							panel.remove(l);
							panel.add(l, (Integer) nowcard.getLayer());
						}
					}

					// �ŵ�tablepile��
					else {
						if (nowCenterY > 300) {
							boolean ismove = false;
							// �ŵ���һ��tablepile��
							for (int i = 0; i < 7 && !ismove; i++) {
								// �������������Է���
								if (nowCenterX > 20 + 170 * i
										&& nowCenterX < 170 + 170 * i)
									// �����ɫ�������������
									if (InsideData.tablepile[i]
											.isSuitable(nowcard)) {
										InsideData.tablepile[j].delete();
										InsideData.tablepile[i].add(nowcard);
										nowcard.setX(20 + 170 * i);
										nowcard.setY(300 + 20 * (InsideData.tablepile[i]
												.length() - 1));
										nowcard.setLayer(InsideData.tablepile[i]
												.length());
										panel.remove(l);
										panel.add(
												l,
												(Integer) InsideData.tablepile[i]
														.length());
										// ����ɹ�������ѭ��
										ismove = true;
									}
							}
							// ���벻�ɹ�������ԭλ��
							if (!ismove) {
								panel.remove(l);
								panel.add(l, (Integer) nowcard.getLayer());
							}
						}
						// ���겻���ʣ�����ԭλ��
						else {
							panel.remove(l);
							panel.add(l, (Integer) nowcard.getLayer());

						}
					}
					l.setLocation(nowcard.getX(), nowcard.getY());// ��������ʾͻص�ԭ��
				}
				// �ƶ�����,ֻ����tablepile���ƶ�
				else { // �ƶ��Ƶ�����
					int numberOfMoveCard = number
							- InsideData.tablepile[j].getTopCardNumber() + 1;
					// ��������ǵڼ�����
					int nowIndex = InsideData.tablepile[j].length()
							- numberOfMoveCard;
					// ����ȡ������temppile��
					for (int i = 0; i < numberOfMoveCard; i++) {
						Card card = InsideData.tablepile[j].delete();
						InsideData.temppile.add(card);
					}

					if (nowCenterY > 300) {
						boolean ismove = false;
						// ������һ��tablepile��
						for (int i = 0; i < 7 && !ismove; i++) {
							// �����������򣬳��Է���
							if (nowCenterX > 20 + 170 * i
									&& nowCenterX < 170 + 170 * i)
								// �����ɫ�͵������ʣ������
								if (InsideData.tablepile[i].isSuitable(nowcard)) {
									for (int k = 0; k < numberOfMoveCard; k++) {
										Card card = InsideData.temppile
												.delete();
										InsideData.tablepile[i].add(card);
										card.setX(20 + 170 * i);
										card.setY(300 + 20 * (InsideData.tablepile[i]
												.length() - 1));
										card.setLayer(InsideData.tablepile[i]
												.length());

										String tempCardKind = card.getKind();
										int tempCardNumber = card.getNumber();
										JLabel tempCard;
										if (tempCardKind.equals("RP")) {
											tempCard = RP_card[tempCardNumber - 1];
										} else if (tempCardKind.equals("RD")) {
											tempCard = RD_card[tempCardNumber - 1];
										} else if (tempCardKind.equals("BF")) {
											tempCard = BF_card[tempCardNumber - 1];
										} else {
											tempCard = BP_card[tempCardNumber - 1];
										}

										panel.remove(tempCard);
										panel.add(
												tempCard,
												(Integer) InsideData.tablepile[i]
														.length());
										tempCard.setLocation(card.getX(),
												card.getY());// ��������ʾͻص�ԭ��
									}
									// ����ɹ�������ѭ��
									ismove = true;
								}
						}
						// ���벻�ɹ�������ԭλ��
						if (!ismove) {

							for (int k = 0; k < numberOfMoveCard; k++) {
								Card card = InsideData.temppile.delete();
								InsideData.tablepile[j].add(card);
								card.setX(20 + 170 * j);
								card.setY(300 + 20 * (InsideData.tablepile[j]
										.length() - 1));
								card.setLayer(InsideData.tablepile[j].length());

								String tempCardKind = card.getKind();
								int tempCardNumber = card.getNumber();
								JLabel tempCard;

								if (tempCardKind.equals("RP")) {
									tempCard = RP_card[tempCardNumber - 1];
								} else if (tempCardKind.equals("RD")) {
									tempCard = RD_card[tempCardNumber - 1];
								} else if (tempCardKind.equals("BF")) {
									tempCard = BF_card[tempCardNumber - 1];
								} else {
									tempCard = BP_card[tempCardNumber - 1];
								}

								panel.remove(tempCard);
								panel.add(tempCard,
										(Integer) InsideData.tablepile[j]
												.length());
								tempCard.setLocation(card.getX(), card.getY());// ��������ʾͻص�ԭ��
							}
						}
					} else// ����
					{
						for (int k = 0; k < numberOfMoveCard; k++) {
							Card card = InsideData.temppile.delete();
							InsideData.tablepile[j].add(card);
							card.setX(20 + 170 * j);
							card.setY(300 + 20 * (InsideData.tablepile[j]
									.length() - 1));
							card.setLayer(InsideData.tablepile[j].length());

							String tempCardKind = card.getKind();
							int tempCardNumber = card.getNumber();
							JLabel tempCard;

							if (tempCardKind.equals("RP")) {
								tempCard = RP_card[tempCardNumber - 1];
							} else if (tempCardKind.equals("RD")) {
								tempCard = RD_card[tempCardNumber - 1];
							} else if (tempCardKind.equals("BF")) {
								tempCard = BF_card[tempCardNumber - 1];
							} else {
								tempCard = BP_card[tempCardNumber - 1];
							}

							panel.remove(tempCard);
							panel.add(tempCard,
									(Integer) InsideData.tablepile[j].length());
							tempCard.setLocation(card.getX(), card.getY());// ��������ʾͻص�ԭ��
						}
					}
				}
			}
			panel.updateUI();
		}
	}

	// tablepile��δ�����Ƶļ�����
	private class MouseAdapter2 extends MouseAdapter {
		
		// �������δ�������Ʒ���
		public void mouseReleased(MouseEvent e) {
			JLabel l = (JLabel) e.getSource();
			int x = l.getLocation().x;
			int y = l.getLocation().y;
			int numberTablePile = (x - 20) / 170;
			int layer = (y - 300) / 20;
			
			if((layer+1)==InsideData.tablepile[numberTablePile].length())
			{panel.remove(l);
			panel.updateUI();
			Card card = InsideData.tablepile[numberTablePile].getCard(layer);
			String Kind = card.getKind();
			int Number = card.getNumber();

			JLabel nowIndexCard;
			if (Kind.equals("RP")) {
				nowIndexCard = RP_card[Number - 1];
			} else if (Kind.equals("RD")) {
				nowIndexCard = RD_card[Number - 1];
			} else if (Kind.equals("BF")) {
				nowIndexCard = BF_card[Number - 1];
			} else {
				nowIndexCard = BP_card[Number - 1];
			}

			nowIndexCard.setBounds(x, y, 150, 200);
			panel.add(nowIndexCard, (Integer) (layer + 1));
			panel.updateUI();
			}

		}

	}

	// �����Ƶ������ק������
	private class MouseMotionListener1 implements MouseMotionListener {
		// ͨ�������ק���һ���ƻ�һ���Ƶ��ƶ�
		public void mouseDragged(MouseEvent e) {
			JLabel l = (JLabel) e.getSource();
			String str = l.getIcon().toString();
			String kind = str.substring(0, 2);
			int number = Integer.parseInt(str.substring(2, 4));

			Card nowcard;
			if (kind.equals("RP"))
				nowcard = InsideData.getcard.get(number - 1);
			else if (kind.equals("RD"))
				nowcard = InsideData.getcard.get(number - 1 + 13);
			else if (kind.equals("BP"))
				nowcard = InsideData.getcard.get(number - 1 + 26);
			else
				nowcard = InsideData.getcard.get(number - 1 + 39);

			// ��tablepileȡ����,���ܶ������ƶ�
			if (nowcard.getY() >= 300) {
				// �ҳ�����Ƶ�������һ��tablepile
				int j;
				if (nowcard.getX() == 20)
					j = 0;
				else if (nowcard.getX() == 190)
					j = 1;
				else if (nowcard.getX() == 360)
					j = 2;
				else if (nowcard.getX() == 530)
					j = 3;
				else if (nowcard.getX() == 700)
					j = 4;
				else if (nowcard.getX() == 870)
					j = 5;
				else
					j = 6;

				int numberOfMoveCard = number
						- InsideData.tablepile[j].getTopCardNumber() + 1;
				int nowIndex = InsideData.tablepile[j].length()
						- numberOfMoveCard;
				for (int i = 0; i < numberOfMoveCard; i++) {
					Card card = InsideData.tablepile[j].getCard(nowIndex + i);
					String nowIndexCardKind = card.getKind();
					int nowIndexCardNumber = card.getNumber();
					JLabel nowIndexCard;

					if (nowIndexCardKind.equals("RP")) {
						nowIndexCard = RP_card[nowIndexCardNumber - 1];
					} else if (nowIndexCardKind.equals("RD")) {
						nowIndexCard = RD_card[nowIndexCardNumber - 1];
					} else if (nowIndexCardKind.equals("BF")) {
						nowIndexCard = BF_card[nowIndexCardNumber - 1];
					} else {
						nowIndexCard = BP_card[nowIndexCardNumber - 1];
					}

					nowIndexCard.setLocation(nowIndexCard.getX() + e.getX()
							- pressX, nowIndexCard.getY() + e.getY() - pressY);

				}

			}
			// ��discard����suitpileȡ�����ƣ�ֻ������һ��
			else {
				l.setLocation(l.getX() + e.getX() - pressX, l.getY() + e.getY()
						- pressY);
			}

		}

		public void mouseMoved(MouseEvent e) {
		}
	}

	// ���ӻ�ͼ�ν��湹����
	public Window() {

		window = new JFrame("ֽ��");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocation(300, 50);
		panel = new JLayeredPane();
		panel.setPreferredSize(new Dimension(1200, 700));
		panel.setLayout(null);
		Icon icon = new ImageIcon("background.jpg");
		JLabel background = new JLabel();
		background.setIcon(icon);
		background.setBounds(0, 0, 1200, 700);
		panel.add(background, (Integer) 0);
		window.getContentPane().add(panel);
		window.pack();
		window.setVisible(true);

		// ����discard��û���Ƶ���ʾlabel
		no_card = new JLabel();
		icon = new ImageIcon(("nocard.png"));
		no_card.setIcon(icon);

		// ��52���Ƶ�ͼ�α�ʾ���г�ʼ��
		for (int i = 0; i < 13; i++) {
			RP_card[i] = new JLabel();
			if (i < 9)
				icon = new ImageIcon(("RP0" + (1 + i) + ".png"));
			else
				icon = new ImageIcon(("RP" + (1 + i) + ".png"));
			RP_card[i].setIcon(icon);
			Card card = InsideData.getcard.get(i);
			RP_card[i].addMouseListener(new MouseAdapter1());
			RP_card[i].addMouseMotionListener(new MouseMotionListener1());
		}
		for (int i = 0; i < 13; i++) {
			RD_card[i] = new JLabel();
			if (i < 9)
				icon = new ImageIcon(("RD0" + (1 + i) + ".png"));
			else
				icon = new ImageIcon(("RD" + (1 + i) + ".png"));
			RD_card[i].setIcon(icon);
			Card card = InsideData.getcard.get(i + 13);
			RD_card[i].addMouseListener(new MouseAdapter1());
			RD_card[i].addMouseMotionListener(new MouseMotionListener1());
		}
		for (int i = 0; i < 13; i++) {
			BP_card[i] = new JLabel();
			if (i < 9)
				icon = new ImageIcon(("BP0" + (1 + i) + ".png"));
			else
				icon = new ImageIcon(("BP" + (1 + i) + ".png"));
			BP_card[i].setIcon(icon);
			Card card = InsideData.getcard.get(i + 26);
			BP_card[i].addMouseListener(new MouseAdapter1());
			BP_card[i].addMouseMotionListener(new MouseMotionListener1());
		}
		for (int i = 0; i < 13; i++) {
			BF_card[i] = new JLabel();
			if (i < 9)
				icon = new ImageIcon(("BF0" + (1 + i) + ".png"));
			else
				icon = new ImageIcon(("BF" + (1 + i) + ".png"));
			BF_card[i].setIcon(icon);
			Card card = InsideData.getcard.get(i + 39);
			BF_card[i].addMouseListener(new MouseAdapter1());
			BF_card[i].addMouseMotionListener(new MouseMotionListener1());
		}

		// ��discard�İ�ť���г�ʼ��
		JButton discard_button = new JButton();
		icon = new ImageIcon("back.jpg");
		discard_button.setIcon(icon);
		discard_button.addActionListener(new Listener1());
		discard_button.setBounds(20, 20, 150, 200);
		panel.add(discard_button, (Integer) 1);

		// ��δ�������Ʒ���tablepile
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < InsideData.tablepile[i].length() - 1; j++) {
				icon = new ImageIcon("back.jpg");
				JLabel back = new JLabel();
				back.addMouseListener(new MouseAdapter2());
				back.setIcon(icon);
				back.setBounds(20 + i * 170, 300 + j * 20, 150, 200);
				panel.add(back, (Integer) (j + 1));
			}
		}

		// ���Ѿ�����7���Ʒ���tablepile��
		for (int i = 0; i < 7; i++) {
			int x = 0, y = 0;
			String kind = InsideData.tablepile[i].getCard(i).getKind();
			int number = InsideData.tablepile[i].getCard(i).getNumber();
			x = InsideData.tablepile[i].getCard(i).getX();
			y = InsideData.tablepile[i].getCard(i).getY();
			if (kind.equals("RP")) {
				RP_card[number - 1].setBounds(x, y, 150, 200);
				panel.add(RP_card[number - 1], (Integer) (i + 1));
			} else if (kind.equals("RD")) {
				RD_card[number - 1].setBounds(x, y, 150, 200);
				panel.add(RD_card[number - 1], (Integer) (i + 1));
			} else if (kind.equals("BF")) {
				BF_card[number - 1].setBounds(x, y, 150, 200);
				panel.add(BF_card[number - 1], (Integer) (i + 1));
			} else {
				BP_card[number - 1].setBounds(x, y, 150, 200);
				panel.add(BP_card[number - 1], (Integer) (i + 1));
			}
		}

	}

}
