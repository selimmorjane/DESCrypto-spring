package com.supgalilee.DESCrypto;

import java.util.BitSet;


public class DES {
	
	//Clé publique
	static String cle = "0001001100110100010101110111100110011011101111001101111111110001";
	
	final static int PC1[] = {
			57, 49, 41, 33, 25, 17, 9,
	        1, 58, 50, 42, 34, 26, 18,
	        10, 2, 59, 51, 43, 35, 27,
	        19, 11, 3, 60, 52, 44, 36,
	        63, 55, 47, 39, 31, 23, 15,
	        7, 62, 54, 46, 38, 30, 22,
	        14, 6, 61, 53, 45, 37, 29,
	        21, 13, 5, 28, 20, 12, 4
	};
	
	final static int PC2[] = {
			14, 17, 11, 24, 1, 5, 3, 28,
	        15, 6, 21, 10, 23, 19, 12, 4,
	        26, 8, 16, 7, 27, 20, 13, 2,
	        41, 52, 31, 37, 47, 55, 30, 40,
	        51, 45, 33, 48, 44, 49, 39, 56,
	        34, 53, 46, 42, 50, 36, 29, 32
	};
	
	final static int IP[] = {
			  58, 50, 42, 34, 26, 18, 10, 2,
		      60, 52, 44, 36, 28, 20, 12, 4,
		      62, 54, 46, 38, 30, 22, 14, 6,
		      64, 56, 48, 40, 32, 24, 16, 8,
		      57, 49, 41, 33, 25, 17, 9, 1,
		      59, 51, 43, 35, 27, 19, 11, 3,
		      61, 53, 45, 37, 29, 21, 13, 5,
		      63, 55, 47, 39, 31, 23, 15, 7
	};
	
	final static int E[] = {
			32, 1, 2, 3, 4, 5,
		     4, 5, 6, 7, 8, 9,
		     8, 9, 10, 11, 12, 13,
		     12, 13, 14, 15, 16, 17,
		     16, 17, 18, 19, 20, 21,
		     20, 21, 22, 23, 24, 25,
		     24, 25, 26, 27, 28, 29,
		     28, 29, 30, 31, 32, 1
	};
	
	final static int S1[][] = {
			{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
			{0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
			{4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
			{15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
	};
	
	final static int S2[][] = {
			 {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
			 {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
			 {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
			 {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
	};
	
	final static int S3[][] = {
			 {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
			 {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
			 {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
			 {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
	};
	
	final static int S4[][] = {
			{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
	 		{13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
			{10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
	 		{3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
	};	
	
	final static int S5[][] = {
			{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
			{14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
			{4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
			{11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
	};	
	
	final static int S6[][] = {
			{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
			{10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
			{9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
			{4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
	};	
	
	final static int S7[][] = {
			{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
			{13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
			{1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
			{6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
	};	
	
	final static int S8[][] = {
			{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
			{1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
			{7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
			{2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
	};
	
	final static int P[] = {
			16, 7, 20, 21, 29, 12, 28, 17,
		    1, 15, 23, 26, 5, 18, 31, 10,
		    2, 8, 24, 14, 32, 27, 3, 9,
		    19, 13, 30, 6, 22, 11, 4, 25
	};
	
	final static int IP_1[] = {
			40, 8, 48, 16, 56, 24, 64, 32,
	        39, 7, 47, 15, 55, 23, 63, 31,
	        38, 6, 46, 14, 54, 22, 62, 30,
	        37, 5, 45, 13, 53, 21, 61, 29,
	        36, 4, 44, 12, 52, 20, 60, 28,
	        35, 3, 43, 11, 51, 19, 59, 27,
	        34, 2, 42, 10, 50, 18, 58, 26,
	        33, 1, 41, 9, 49, 17, 57, 25
	};
	
	final static int Shift[] = {
			1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1
	};
	
	public static String crypt(String str)
	{
		String[] messages = fromStringToBinaryString(str);
		String resultat = "";		
		for(String message : messages) {
			BitSet bitsetmessage = bitSetFromString(message);
			BitSet bitsetcle = bitSetFromString(cle);		

			BitSet chiffrer = chiffrer(bitsetmessage, bitsetcle, 56);			
			String fromBit = stringFromBitSet(chiffrer);
			resultat += fromBit;
			
		}
		return resultat;
	}
	
	public static String decrypt(String str)
	{
		
		String[] messages = fromStringToBinaryString(str);
		String resultat = "";		
		for(String message : messages) {
			BitSet bitsetmessage = bitSetFromString(message);
			BitSet bitsetcle = bitSetFromString(cle);			
			
			BitSet chiffrer = chiffrer(bitsetmessage, bitsetcle, 56);			
			BitSet dechiffrer = dechiffrer(chiffrer, bitsetcle, 56);
			
			
			String fromBit = stringFromBitSet(dechiffrer);
			resultat += fromBit;
		}
			return stringFromBinaryString(resultat);
	}
	
	
	public static String[] fromStringToBinaryString(String str) {
		byte[] octets = str.getBytes();
		StringBuilder binaire = new StringBuilder();
		for(byte octet : octets) {
			int val = octet;
			for(int i=0; i<8; i++) {
				binaire.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
		}
		return binaire.toString().split("(?<=\\G.{64})");
	}
	
	//Retourne un BitSet avec les bits set en fonction du tableau d'entier
	public static BitSet permuter(BitSet tab, int[] cle)
	{
		BitSet tmp = new BitSet(10);
		int j=0;
		for (int i:cle) {
			tmp.set(j,tab.get(i-1));
			j++;
		}
		return tmp;
	}
	
	//Retourne la première moitié de notre BitSet
	public static BitSet left_shift(BitSet tab, int taille)
	{
		BitSet left = new BitSet(taille/2);
		for(int i=0; i<taille/2; i++) {
			left.set(i, tab.get(i));
		}
		return left;
	}
	
	//Retourne la seconde moitié de notre BitSet
	public static BitSet right_shift(BitSet tab, int taille)
	{
		BitSet right = new BitSet(taille/2);
		for (int i=taille/2; i<taille; i++) {
			right.set(i-taille/2, tab.get(i));
		}
		return right;
	}
	
	//Décalle de 1 bit vers la gauche la première moitié puis la second moitié de notre BitSet
	public static BitSet shift(BitSet tab, int taille)
	{
		BitSet left_tab = left_shift(tab, taille);
		BitSet right_tab = right_shift(tab, taille);
		BitSet tmp_left = new BitSet();
		BitSet tmp_right = new BitSet();
		
		for (int i = 0 ; i<taille/2 -1 ; i++) {
			tmp_left.set(i, left_tab.get(i+1));
			tmp_right.set(i, right_tab.get(i+1));			
		}
		
		tmp_left.set(taille/2 - 1, left_tab.get(0));
		tmp_right.set(taille/2 - 1, right_tab.get(0));
		BitSet shift = concatBitSet(tmp_left, tmp_right, taille);
		 
		return shift;
	}
	
	public static BitSet chiffrer(BitSet message, BitSet cle, int taille) {
		BitSet shifted = cle;
		BitSet Kn = new BitSet(taille);
		BitSet IP_msg = permuter(message, IP);
		BitSet L = left_shift(IP_msg, 64);
		BitSet R = right_shift(IP_msg, 64);
		BitSet temp;
		for(int i=0; i<16; i++) {
			for(int j=0; j<Shift[i]; j++) {
				shifted = shift(shifted, taille);
			}
			Kn = permuter(shifted, PC2);
			BitSet f = f(R, Kn, taille);
			L.xor(f);
			temp = L;
			L = R;
			R = temp;
		}
		BitSet RL = concatBitSet(R, L, 64);
		BitSet fin = permuter(RL, IP_1);
		return fin;
	}
	
	public static BitSet dechiffrer(BitSet message, BitSet cle, int taille) {
		BitSet shifted = cle;
		BitSet Kn[] = new BitSet[16];
		BitSet IP_msg = permuter(message, IP);
		BitSet L = left_shift(IP_msg, 64);
		BitSet R = right_shift(IP_msg, 64);
		BitSet temp;
		for(int i=0; i<16; i++) {
			for(int j=0; j<Shift[i]; j++) {
				shifted = shift(shifted, taille);
			}
			Kn[i] = permuter(shifted, PC2);
		}
		
		for(int i=16; i>0; i--) {
			BitSet f = f(R, Kn[i-1], taille);
			L.xor(f);
			temp = L;
			L = R;
			R = temp;
		}
		
		BitSet RL = concatBitSet(R, L, 64);
		BitSet fin = permuter(RL, IP_1);
		return fin;
	}
	
	public static BitSet f(BitSet right_tab, BitSet cle, int taille) {
		BitSet rn_1 = permuter(right_tab, E);
		rn_1.xor(cle);
		String concat = "";
		for(int i=0; i<8; i++) {
			BitSet B = bitSetdivider(rn_1,i);
			BitSet S = sBox(B,i+1);
			concat += bitSetTo4String(S);
		}
		BitSet concatBit = bitSetFromString(concat);
		BitSet f = permuter(concatBit, P);
		return f;
	}
	
	public static String bitSetTo4String(BitSet tab) {
		String bitString = Integer.toBinaryString(bitSetToInt(tab));
		while(bitString.length() < 4) {
			bitString ="0" + bitString;
		}
		return bitString;
	}
	
	public static String bitSetToString(BitSet tab, int taille) {
		String bitString = Integer.toBinaryString(bitSetToInt(tab));
		return bitString;
	}
	public static BitSet bitSetdivider(BitSet tab, int i) {
		BitSet divided = new BitSet(6);
		for(int j=0; j<6; j++) {
			divided.set(j, tab.get((i*6)+j));
		}
		return divided;
	}
	
	public static BitSet sBox(BitSet tab, int i) {
		String strRange = String.valueOf(booleantoInt(tab.get(0))) + String.valueOf(booleantoInt(tab.get(5)));
		int range = Integer.parseInt(strRange, 2);
		String strColonne = String.valueOf(booleantoInt(tab.get(1))) + String.valueOf(booleantoInt(tab.get(2))) 
		+ String.valueOf(booleantoInt(tab.get(3))) + String.valueOf(booleantoInt(tab.get(4)));
		int colonne = Integer.parseInt(strColonne, 2);
		int sboxValue = 0;
		switch(i) {
			case 1:
				sboxValue = S1[range][colonne];
				break;
			case 2:
				sboxValue = S2[range][colonne];
				break;
			case 3:
				sboxValue = S3[range][colonne];
				break;
			case 4:
				sboxValue = S4[range][colonne];
				break;
			case 5:
				sboxValue = S5[range][colonne];
				break;
			case 6:
				sboxValue = S6[range][colonne];
				break;
			case 7:
				sboxValue = S7[range][colonne];
				break;
			case 8:
				sboxValue = S8[range][colonne];
				break;
		}
		return BitSet.valueOf(new long[] {sboxValue});
	}
	
	public static BitSet addBitSet(BitSet tab1, BitSet tab2) {
		int add = bitSetToInt(tab1) + bitSetToInt(tab2);
		
		return BitSet.valueOf(new long[] {add});		
	}
	
	public static BitSet rightaPlat(BitSet right_tab,int taille) {
		BitSet temp = new BitSet(4);
		for(int i=taille; i<right_tab.length(); i++) {
			if (right_tab.get(i)) {
				temp.set(i - taille);
			}
		}
		return temp;
	}
	
	public static BitSet concatBitSet(BitSet tab1, BitSet tab2, int taille) {
		BitSet concat = new BitSet();
		for(int i=0; i<taille; i++) {
			if(tab1.get(i)) {
				concat.set(i);
			}
			if(tab2.get(i)) {
				concat.set(i+taille/2);
			}
		}
		return concat;
	}
	
	public static BitSet bitSetFromString(String bit) {
		BitSet fromString = new BitSet(bit.length());
		for(int i=0; i< bit.length(); i++) {
			if(bit.charAt(i) == '1') {
				fromString.set(i);
			}
		}
		return fromString;
	}
	
	public static String stringFromBitSet(BitSet bit) {
		StringBuilder fromBitSet = new StringBuilder();
		for(int i=0; i<bit.length(); i++) {
			fromBitSet.append(bit.get(i) ? 1 : 0);
		}
		return fromBitSet.toString();
	}
	
	public static String stringFromBinaryString(String binary) {
		StringBuilder fromBinaryString = new StringBuilder();
		char[] tab = binary.toCharArray();
		for (int i = 0; i < tab.length; i+=8) {
		    int intOfString = 0, indexBinaire = 0;
		    for (int j = 7; j>= 0; j--) {
		       if (tab[i+j] == '1') {
		        	intOfString += 1 << indexBinaire;
		        }
		        indexBinaire++;
		    }
		    fromBinaryString.append(Character.toChars(intOfString));
		}
		return fromBinaryString.toString();
	}
	
	public static int bitSetToInt(BitSet tab) {
		int intValue = 0;
		for (int i =0; i<tab.length(); i++) {
			if(tab.get(i)) {
				intValue |= (1 << i);
			}
		}
		return intValue;
	}
	
	public static int booleantoInt(Boolean b) {
		if (b)
			return 1;		
		return 0;
	}
	
	public static String bitSetToString(BitSet tab) {
		String binary = "";
		for(int i=0; i < 56; i++) {
			if(tab.get(i)) {
				binary += "1";
			} else {
				binary += "0";
			}
		}
		return binary;
	}
	
	public static void main(String [] args) {
		String strMessage = "Heheheheheheehhe";
		String[] messages = fromStringToBinaryString(strMessage);
		String cle = "0001001100110100010101110111100110011011101111001101111111110001";
		String resultat = "";
		
		for(String message : messages) {
			BitSet bitsetmessage = bitSetFromString(message);
			BitSet bitsetcle = bitSetFromString(cle);		
			System.out.println(bitsetmessage);
			System.out.println(bitsetcle);
			
			BitSet chiffrer = chiffrer(bitsetmessage, bitsetcle, 56);
		
			BitSet dechiffrer = dechiffrer(chiffrer, bitsetcle, 56);
			
			String fromBit = stringFromBitSet(dechiffrer);
			resultat += fromBit;
		}
		System.out.println(stringFromBinaryString(resultat));
	}
}



