package cn.miao.test;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class TestClass {

	public static void main(String[] args) {
		int positionSize = 24;
		List<Integer> positionList =  new ArrayList<Integer>(){};
		for (Integer i = 0; i < positionSize; i++) {
			positionList.add(i);
		}
		System.err.println("positionList:"+positionList);
		Random random = new Random();
		List<Integer[]> randomPositionList =  new ArrayList<Integer[]>(){};
		for(int i = positionSize; i > 0; i--){
			int index = i==1?0:random.nextInt(i-1);
			Integer value = positionList.get(index);
			Integer[] chickInfo = new Integer[5];
			chickInfo[0] = value;//鸡位置
			chickInfo[1] = 0;//鸡状态（0：未解锁，1：已解锁）
			chickInfo[2] = (positionSize-i)/8;//鸡种类（0：小鸡，1：母鸡，2：公鸡）
			chickInfo[3] = ((positionSize-i)/2)%4;//鸡宝箱颜色（0：木，1：银，2：金，3：钻石）
			chickInfo[4] = (positionSize-i)/2;//奖励（0：10元全场优惠券，1：20元全场优惠券，2：30元全场优惠券，3：50元全场优惠券  4：100妙币，5：200妙币，6：400妙币，7：800妙币  8：1000妙币，9：1200妙币，10：1500妙币，11：2000妙币）
			randomPositionList.add(chickInfo);
			positionList.remove(index);
		}
		System.err.println("randomPositionList:"+ JSON.toJSON(randomPositionList)+"  randomPositionList"+randomPositionList.size());
		Map<Integer, String> matchMap = new HashMap<Integer, String>();
		for (int i = 0; i < randomPositionList.size(); i=i+2) {
			matchMap.put(randomPositionList.get(i)[0], randomPositionList.get(i+1)[0] + "#" + randomPositionList.get(i+1)[4]);
			matchMap.put(randomPositionList.get(i+1)[0], randomPositionList.get(i)[0] + "#" + randomPositionList.get(i+1)[4]);
		}
		System.err.println("matchMap："+JSON.toJSON(matchMap));
	}

}
