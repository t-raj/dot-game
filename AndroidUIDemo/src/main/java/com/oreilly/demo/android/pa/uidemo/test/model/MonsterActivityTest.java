package com.oreilly.demo.android.pa.uidemo.test.model;

import com.oreilly.demo.android.pa.uidemo.model.MonsterActivity;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Created by Kyle on 4/29/2015.
 */
public class MonsterActivityTest {

    public MonsterActivity monsterActivityActivity1 = new MonsterActivity();
    public MonsterActivity monsterActivityActivity2 = new MonsterActivity();



    @Test
    public void testMonsterMove(){
        int[][] matrix2 = monsterActivityActivity2.getMonsterMatrix();
        monsterActivityActivity1.monsterGridMove();
        int[][] matrix1 = monsterActivityActivity1.getMonsterMatrix();
        assertFalse(matrix1 == matrix2);
    }
}
