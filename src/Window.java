import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

//创建纸牌游戏的窗口
public class Window {
	// 窗口
	private JFrame window;
	// 可以为每一个添加进去的组件设置层次的面板
	private JLayeredPane panel;
	// 供牌区为空的标志
	private JLabel no_card;
	// 红桃牌组
	private JLabel[] RP_card = new JLabel[13];
	// 黑桃牌组
	private JLabel[] BP_card = new JLabel[13];
	// 方片牌组
	private JLabel[] RD_card = new JLabel[13];
	// 梅花牌组
	private JLabel[] BF_card = new JLabel[13];
	// 点击牌的横纵坐标，用于设定牌的位置
	private static int pressX, pressY;
	// 供牌区的计数器，用于滚动更新牌
	private static int count = 0;
	

	// discard的按钮监听类
	private class Listener1 implements ActionListener {

		// 每次点击按钮就换一张牌
		public void actionPerformed(ActionEvent e) {
			// count为0时，处于discard开始位置，开始翻第一张牌
			if (count == 0)
				panel.remove(no_card);

			// 只要count小于discard的长度就可以继续往下翻牌，通过内部存储的数据来找到属于这个数据的label
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
			// count等于discard的长度说明到了discard的末端，要清楚所有存在的牌重头发牌，此时设置提示标志
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

	// 所有牌的鼠标按压和松开监听类
	private class MouseAdapter1 extends MouseAdapter {

		// 鼠标按压后，可以得知按压哪一张牌
		// 将坐标信息传递给拖拽监听用于纸牌的移动，将原始坐标和层次信息传递给释放监听用于纸牌无法移动回到原位置，同时将点击的纸牌放到最上层
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

			// 选中的纸牌在tablepile中
			if (nowcard.getY() >= 300) { // 得到在哪个tablepile中
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

				// 要移动纸牌的张数
				int numberOfMoveCard = number
						- InsideData.tablepile[j].getTopCardNumber() + 1;

				// 选中纸牌在第几张
				int nowIndex = InsideData.tablepile[j].length()
						- numberOfMoveCard;

				// 将要移动的纸牌放到最上面
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
			// 选中的纸牌在discard或者suitpile中
			else {
				panel.remove(l);
				panel.add(l, (Integer) 60);
			}
		}

		public void mouseReleased(MouseEvent e) {
			// 得到选中纸牌的图形信息和数据信息
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

			// 鼠标点击释放时选中纸牌左上角的坐标
			int nowX = l.getX() + e.getX() - pressX;
			int nowY = l.getY() + e.getY() - pressY;
			// 鼠标点击释放时选中纸牌中心的坐标
			int nowCenterX = nowX + 75;
			int nowCenterY = nowY + 100;

			// 从discard或者suitpile取出牌
			if (nowcard.getY() < 250) {
				// 从discard取出牌
				if (nowcard.getX() < 350) {
					// 放到suitpile中
					if (nowCenterY > 20 && nowCenterY < 220) {
						boolean ismove = false;
						// 放到哪一个suitpile中
						for (int i = 0; i < 4 && !ismove; i++) {
							// 如果坐标适合，则尝试放入
							if (nowCenterX > 530 + i * 170
									&& nowCenterX < 680 + i * 170)
								// 如果花色和点数符合则放入
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
									// 已经放入，跳出循环
									ismove = true;

									// 判断，若suitpile都满了则赢了，出现提示信息
									if (InsideData.suitpile[0].length() == 13
											&& InsideData.suitpile[1].length() == 13
											&& InsideData.suitpile[2].length() == 13
											&& InsideData.suitpile[3].length() == 13)
										JOptionPane.showMessageDialog(null,
												"You win!");
								}
						}
						// 放入不成功，返回原位置
						if (!ismove) {
							panel.remove(l);
							panel.add(l, (Integer) nowcard.getLayer());
						}
					}

					// 放到tablepile里
					else {
						if (nowCenterY > 300) {
							boolean ismove = false;
							// 放到哪一个tablepile里
							for (int i = 0; i < 7 && !ismove; i++) {
								// 如果坐标合适尝试放入
								if (nowCenterX > 20 + 170 * i
										&& nowCenterX < 170 + 170 * i)
									// 如果花色和点数符合则放入
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
										// 放入成功跳出循环
										ismove = true;
									}

							}
							// 放入不成功返回原位置
							if (!ismove) {
								panel.remove(l);
								panel.add(l, (Integer) nowcard.getLayer());
							}

						}
						// 坐标不合适，返回原位置
						else {
							panel.remove(l);
							panel.add(l, (Integer) nowcard.getLayer());

						}
					}

				}

				// 从suitpile取出牌
				else {
					// 从哪一个suitpile中取出牌
					int j;
					if (nowcard.getX() == 530)
						j = 0;
					else if (nowcard.getX() == 700)
						j = 1;
					else if (nowcard.getX() == 870)
						j = 2;
					else
						j = 3;

					// 放到suitpile中
					if (nowCenterY > 20 && nowCenterY < 220) {
						boolean ismove = false;
						// 放到哪一个suitpile中
						for (int i = 0; i < 4 && !ismove; i++) {
							// 如果坐标合适则尝试放入
							if (nowCenterX > 530 + i * 170
									&& nowCenterX < 680 + i * 170)
								// 如果花色和点数合适则放入
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
									// 放入成功，跳出循环
									ismove = true;
								}
						}
						// 放入不成功返回原位置
						if (!ismove) {
							panel.remove(l);
							panel.add(l, (Integer) nowcard.getLayer());
						}
					}

					// 放到tablepile里
					else {
						if (nowCenterY > 300) {
							boolean ismove = false;
							// 放到哪一个tablepile中
							for (int i = 0; i < 7 && !ismove; i++) {
								// 如果坐标合适则尝试放入
								if (nowCenterX > 20 + 170 * i
										&& nowCenterX < 170 + 170 * i)
									// 如果花色点数合适则放入
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
										// 放入成功，跳出循环
										ismove = true;
									}
							}

							// 放入不成功，返回原位置
							if (!ismove) {
								panel.remove(l);
								panel.add(l, (Integer) nowcard.getLayer());
							}
						}
						// 放入坐标不合适返回原位置
						else {
							panel.remove(l);
							panel.add(l, (Integer) nowcard.getLayer());
						}
					}
				}
				l.setLocation(nowcard.getX(), nowcard.getY());// 如果不合适就回到原处
			}

			// 从tablepile取出牌
			else {
				// 从哪一个tablepile取出牌
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
				// 移动一张牌
				if (number == InsideData.tablepile[j].getTopCardNumber()) {
					// 放到suitpile中
					if (nowCenterY > 20 && nowCenterY < 220) {
						boolean ismove = false;
						// 放到哪一个suitpile中
						for (int i = 0; i < 4 && !ismove; i++) {
							// 如果坐标合适则尝试放入
							if (nowCenterX > 530 + i * 170
									&& nowCenterX < 680 + i * 170)
								// 如果花色和点数合适，则放入
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
									// 放入成功，跳出循环
									ismove = true;

									// 判断suitpile是否都满，如果都满，则赢了
									if (InsideData.suitpile[0].length() == 13
											&& InsideData.suitpile[1].length() == 13
											&& InsideData.suitpile[2].length() == 13
											&& InsideData.suitpile[3].length() == 13)
										JOptionPane.showMessageDialog(null,
												"You win!");
								}
						}
						// 移动不成功，返回原位置
						if (!ismove) {
							panel.remove(l);
							panel.add(l, (Integer) nowcard.getLayer());
						}
					}

					// 放到tablepile里
					else {
						if (nowCenterY > 300) {
							boolean ismove = false;
							// 放到哪一个tablepile中
							for (int i = 0; i < 7 && !ismove; i++) {
								// 如果坐标合适则尝试放入
								if (nowCenterX > 20 + 170 * i
										&& nowCenterX < 170 + 170 * i)
									// 如果花色点数合适则放入
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
										// 放入成功，跳出循环
										ismove = true;
									}
							}
							// 放入不成功，返回原位置
							if (!ismove) {
								panel.remove(l);
								panel.add(l, (Integer) nowcard.getLayer());
							}
						}
						// 坐标不合适，返回原位置
						else {
							panel.remove(l);
							panel.add(l, (Integer) nowcard.getLayer());

						}
					}
					l.setLocation(nowcard.getX(), nowcard.getY());// 如果不合适就回到原处
				}
				// 移多张牌,只能向tablepile中移动
				else { // 移动牌的张数
					int numberOfMoveCard = number
							- InsideData.tablepile[j].getTopCardNumber() + 1;
					// 鼠标点击牌是第几张牌
					int nowIndex = InsideData.tablepile[j].length()
							- numberOfMoveCard;
					// 奖牌取出放入temppile中
					for (int i = 0; i < numberOfMoveCard; i++) {
						Card card = InsideData.tablepile[j].delete();
						InsideData.temppile.add(card);
					}

					if (nowCenterY > 300) {
						boolean ismove = false;
						// 放入哪一个tablepile中
						for (int i = 0; i < 7 && !ismove; i++) {
							// 如果坐标合适则，尝试放入
							if (nowCenterX > 20 + 170 * i
									&& nowCenterX < 170 + 170 * i)
								// 如果花色和点数合适，则放入
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
												card.getY());// 如果不合适就回到原处
									}
									// 放入成功，跳出循环
									ismove = true;
								}
						}
						// 放入不成功，返回原位置
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
								tempCard.setLocation(card.getX(), card.getY());// 如果不合适就回到原处
							}
						}
					} else// 不动
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
							tempCard.setLocation(card.getX(), card.getY());// 如果不合适就回到原处
						}
					}
				}
			}
			panel.updateUI();
		}
	}

	// tablepile中未翻开牌的监听类
	private class MouseAdapter2 extends MouseAdapter {
		
		// 点击鼠标后，未翻开的牌翻开
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

	// 所有牌的鼠标拖拽监听类
	private class MouseMotionListener1 implements MouseMotionListener {
		// 通过鼠标拖拽完成一张牌或一堆牌的移动
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

			// 从tablepile取出的,可能多张牌移动
			if (nowcard.getY() >= 300) {
				// 找出点击牌的属于哪一个tablepile
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
			// 从discard或者suitpile取出的牌，只可能是一张
			else {
				l.setLocation(l.getX() + e.getX() - pressX, l.getY() + e.getY()
						- pressY);
			}

		}

		public void mouseMoved(MouseEvent e) {
		}
	}

	// 可视化图形界面构造器
	public Window() {

		window = new JFrame("纸牌");
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

		// 生成discard中没有牌的提示label
		no_card = new JLabel();
		icon = new ImageIcon(("nocard.png"));
		no_card.setIcon(icon);

		// 对52张牌的图形表示进行初始化
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

		// 对discard的按钮进行初始化
		JButton discard_button = new JButton();
		icon = new ImageIcon("back.jpg");
		discard_button.setIcon(icon);
		discard_button.addActionListener(new Listener1());
		discard_button.setBounds(20, 20, 150, 200);
		panel.add(discard_button, (Integer) 1);

		// 将未翻开的牌放入tablepile
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

		// 将已经翻开7张牌放入tablepile中
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
