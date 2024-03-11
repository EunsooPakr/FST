package main.java.cs;

import java.util.Scanner;

public class Rpg {
	public static void controlSample() { //rgp 게임
		int playerHp = 100;			// 플레이어 hp 100 고정
		int playerExp = 0;			// 플레이어 exp 0 시작
		boolean play = true;		
		int restCount = 0; 			// 휴식 횟수 카운트 추가
	    final int MAX_REST_COUNT = 2; 		// 최대 휴식 횟수 설정
		System.out.println("welcom to Java Dungeon");
		System.out.println("원하는 메뉴를 선택하세요");
		Scanner sc = new Scanner(System.in);
		while (play) {						//play가 true일때까지 게임 시작
			if (playerExp>=100) {			//플레이어 exp가 100넘었을때 게임 종료
				System.out.println("던전 공략 완료");
				play = false;
				break;
			}
			System.out.println("----------------------------");
			System.out.println("1.사냥 | 2.쉬기 | 3.게임 종료");
			System.out.println("----------------------------");
			int inNum = sc.nextInt();			//사용자에게 번호 입력받기
			switch (inNum) {
			// 사냥
			case 1:				// 1. 사냥일때 전투 시작
				int rNum = (int) (Math.random() * 40) + 20;			// 랜덤 난수 20~40 체력을 가진 몬스터 체력
				System.out.println(rNum + " 의 체력을 가진 몬스터 등장.");
				while (rNum > 0) {					// 몬스터 체력이 0 이상일때 전투 무한반복
					System.out.println("1.주먹질 | 2.총쏘기 | 3.빤스런");
					int fight = sc.nextInt();			// 공격 메뉴 번호 선택받기
					switch (fight) {				//공격 메뉴 번호에 따라 행동하는 switch 문
					case 1:
						int punch = (int) (Math.random() * 30) + 10;		// 랜덤 난수의 주먹 데미지 측정 10~30
						System.out.println("주먹질로 " + punch + "만큼 때렸다.");
						rNum -= punch;		//몬스터 체력 - 주먹 데미지	
						playerHp -= 20;		//싸움으로 인한 플레이어 hp 20 감소
						System.out.println("현재 남은 체력: " + playerHp);
						System.out.println("현재 남은 몬스터 체력: " + rNum);
						if (playerHp <= 0) {		// 플레이어 체력이 0 이하일때 게임 종료
							System.out.println("사망하셨습니다.");
							rNum = -100;		//몬스터 체력을 음수로 설정하여 전투 종료
							play = false;		//게임 true값을 false로 지정하여 게임 종료
							break;		
						}
						if (rNum <= 0) {			// 몬스터 체력 0 이하가 됐을때
							System.out.println("몬스터 사망");		
							playerExp += 10;		//플레이여 exp 10 추가
							System.out.println("현재 경험치: " + playerExp);
							System.out.println("현재 남은 체력: " + playerHp);

						}
						break;
					case 2:
						int shoot = (int) (Math.random() * 100);		//총 나갈 확률 랜덤 난수로 측정 0~100
						if (shoot > 70) {		//70 이상일때 격발
							System.out.println("격발");
							rNum -= 100;		//한방에 퇴치 후 몬스터 체력 음수로 변환
							System.out.println("현재 남은 몬스터 체력: " + rNum);
							if (rNum <= 0) {		//몬스터 체력 0이하시 경험치 증가
								System.out.println("몬스터 사망");
								playerExp += 10;
								System.out.println("현재 경험치: " + playerExp);
								System.out.println("현재 남은 체력: " + playerHp);
								break;
							}
							playerHp -= 20;		//전투 종료 후 플레이어 hp -20
							System.out.println("현재 남은 체력: " + playerHp);
							if (playerHp <= 0) {
								System.out.println("사망하셨습니다.");
								rNum = -100;
								play = false;
								break;
							}

						} else {
							System.out.println("불발");
							playerHp -= 30;
							System.out.println("현재 남은 몬스터 체력: " + rNum);
							System.out.println("현재 남은 체력: " + playerHp);
							if (playerHp <= 0) {
								System.out.println("사망하셨습니다.");
								rNum = -100;
								play = false;
								break;
							}

						}
						break;
					case 3:
						int run = (int) (Math.random() * 10) + 1;		//도망 확률 랜덤난수로 지정 1~10
						if (run < 5) {		// 도망 확률 5미만시 실패
							System.out.println("빤스런 실패");
							playerHp -= 30;		//패널티 플레이어 체력 -30
							System.out.println("현재 남은 체력: " + playerHp);
							System.out.println("현재 남은 몬스터 체력: " + rNum);
							if (playerHp <= 0) {
								System.out.println("사망하셨습니다.");
								rNum = -100;
								play = false;
								break;
							}
						} else {
							rNum = -100;
							System.out.println("빤쓰런 성공");
							System.out.println("현재 남은 체력: " + playerHp);
							break;
						}

					}

				}
				break;			//case3 탈출 후 게임 메뉴로 이동
			// 휴식
			case 2:
				if (restCount < MAX_REST_COUNT) { // 최대 휴식 횟수 이내일 때만 휴식 가능
                    System.out.println("휴식을 취했습니다.");
                    playerHp += 20;
                    if (playerHp >= 100) {
                        playerHp = 100;
                    }
                    System.out.println("현재 Hp :" + playerHp);
                    restCount++; // 휴식 횟수 증가
                } else {
                	if(restCount>3) {
                		System.out.println("너무 많은 휴식으로 급습당함.");
                		System.out.println("사망하셨습니다.");
                		play = false;
                		break;
                	}
                    System.out.println("더 이상 휴식할 수 없습니다.");
                    restCount++;
                }
				break;		// 휴식 종류 후 게임 메뉴로 이동
			case 3:
				System.out.println("게임을 종료합니다.");
				play = false;
				break;		//false값 전달

			default:
				System.out.println("다시 입력해주세요. ");
				break;

			}

		}

	}
}
