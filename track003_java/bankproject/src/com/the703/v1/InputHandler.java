package com.the703.v1;

import java.util.Scanner;

public class InputHandler {
	private static Scanner scan = new Scanner(System.in);

	public int getInt(String inputMsg) {
		while (true) {
			try {
				System.out.print(inputMsg);
				String input = scan.nextLine();
				return Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력해주세요.");
			}
		}
	}

	public long getLong(String inputMsg) {
		while (true) {
			try {
				System.out.print(inputMsg);
				String input = scan.nextLine();
				return Long.parseLong(input);
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력해주세요.");
			}
		}
	}

	public String getString(String inputMsg) {
		System.out.print(inputMsg);
		return scan.nextLine();
	}
}
