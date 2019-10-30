package util;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import map.objects.enemy.Enemy;


public class SortingUtil {
	
	public SortingUtil() {
		
	}

    /**
     * The given map is sorted by value. We need this to get the nearest Enemy for the towers to shoot.
     * @param unsortMap
     * @return sorted List of enemies
     */
	public static List<Enemy> getSortedListBySortingMapByValue(Map<Enemy, Integer> unsortMap) {
        List<Map.Entry<Enemy, Integer>> list =
                new LinkedList<Map.Entry<Enemy, Integer>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Enemy, Integer>>() {
            public int compare(Map.Entry<Enemy, Integer> o1, Map.Entry<Enemy, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        List<Enemy> result = new ArrayList<Enemy>();  
        for (Map.Entry<Enemy, Integer> entry : list) {
            result.add(entry.getKey());
        }

        return result;
    }
	
	
	//could be usefull later 
	public <K, V extends Comparable<? super V>> Map<K, V> sortMapByValue(Map<K, V> unsortMap) {
        List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
	
}
