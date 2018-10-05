package paramabadham;

import java.util.*;

public class ParamaBhadmBord {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Please Enter No of players : ");
		int noOfPlayers = in.nextInt();
		int currentPosition[] = new int[noOfPlayers + 1];
		int place[] = new int[noOfPlayers + 1];
		for (int i = 1; i < currentPosition.length; i++) {
			currentPosition[i] = 90;
		}
		Boolean endOfGame = true;
		int die[] = { 1, 2, 3, 4, 5, 6 };
		OUTER: while (endOfGame) {
			for (int i = 1; i <= noOfPlayers; i++) {
				System.out.print("player" + i + " Enter y to spin the die : ");

				Boolean player = true;
				INNER: while (player) {
					String choice = in.next();
					if (choice.equalsIgnoreCase("y")) {
						int count = die[new Random().nextInt(die.length)];
						if (100 - place[i] <= 6) { // winning moment
							if (100 - place[i] < count) {
								System.out.println("You got : " + count);
								System.out.println("You Cannot Move....");
								System.out.println("You Needs "
										+ (100 - place[i])
										+ " To Win This Game....\n");
								break INNER;
							} else if (100 - place[i] == count) {
								System.out.println();
								System.out.println("You got : " + count);
								System.out.println("Congrats For Player" + i + "\nYou Won This Game....");
								break OUTER;

							}
						}
						System.out.println("You got : " + count);
						int unit = game(count, currentPosition[i]);
						System.out.println();
						place[i] = unit % 1000;
						currentPosition[i] = unit / 1000;
						player = false;
					} else {
						System.out.println("Your Choice is Wrong Please Enter 'y' : ");
						player = true;
					}
				}

			}

			System.out.println();
			System.out.println("After Each Players Turn Postion of The Players ......");
			for (int i = 1; i < place.length; i++) {
				System.out.print("Player" + i + "\t");
			}
			System.out.println();
			for (int i = 1; i < place.length; i++) {
				System.out.print(place[i] + "\t");
			}
			System.out.println();
			System.out.println("----------------------------------------------------------------\n");
		}

	}

	public static int game(int moves, int position) {
		int board[][] = new int[10][10];

		int start = 1;
		for (int i = 9; i >= 0; i--) {
			if (i % 2 == 1) {
				for (int j = 0; j < 10; j++) {
					board[i][j] = start++;
				}
			} else if (i % 2 == 0 || i == 0) {
				for (int j = 9; j >= 0; j--) {
					board[i][j] = start++;
				}
			}

		}

		int Column = position / 10;
		int row = position % 10;
		int count = 0;
		int f = 0;
		for (int i = Column; i >= 0; i--) {

			if (i % 2 == 1) {
				for (int j = row; j < 10; j++) {
					f = 1;
					count++;
					if (count == moves) {
						Column = i;
						row = j;
						int pos = snackLadder(Column, j); // snack ladder
															// function
															// call
						Column = pos / 10;
						row = pos % 10;
						int place = board[Column][row];

						if (row == 9) {
							Column -= 1;
						} else {
							row++;
						}
						int merge = (((Column * 10) + row) * 1000) + place;
						return merge; /*
									 * adding column row and position in single
									 * number - 1st 2nd digits are column and
									 * row respectively remaining will be the
									 * position
									 */

					}
				}
			} else if (i % 2 == 0 || i == 0) {
				for (int j = (f == 1 ? 9 : row); j >= 0; j--) {
					count++;
					if (count == moves) {
						Column = i;
						row = j;
						int pos = snackLadder(Column, j); // snack ladder
															// function
															// call
						Column = pos / 10;
						row = pos % 10;
						int place = board[Column][row];

						if (row == 0) {
							Column -= 1;
						} else if (Column % 2 == 0 || Column == 0) {
							row--;
						} else {
							row++;
						}
						int merge = (((Column * 10) + row) * 1000) + place;
						return merge;
					}
				}
			}

		}

		return 0;

	}

	public static int snackLadder(int column, int row) {
		int snackMouth[] = { 76, 51, 54, 48, 36, 24, 11, 06, 04 };
		int snackTail[] = { 95, 82, 73, 69, 67, 53, 32, 65, 23 };
		int ladderBottom[] = { 96, 88, 84, 70, 62, 35, 22 };
		int ladderTop[] = { 77, 37, 55, 21, 33, 14, 16 };
		int position = (column * 10) + row;
		for (int i = 0; i < snackMouth.length; i++) {
			if (snackMouth[i] == position) {
				System.out.println("ohhhh Snack bited you...");
				return snackTail[i];
			}
		}
		for (int i = 0; i < ladderBottom.length; i++) {
			if (ladderBottom[i] == position) {
				System.out.println("Hurrayyyy you got the Ladder...");
				return ladderTop[i];
			}
		}
		return (column * 10) + row;

	}

}
