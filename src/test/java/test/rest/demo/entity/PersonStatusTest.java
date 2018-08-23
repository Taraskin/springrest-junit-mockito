package test.rest.demo.entity;

import org.junit.Assert;
import org.junit.Test;

public class PersonStatusTest {

    @Test(expected = IllegalArgumentException.class)
    public void determine_negative_input() {
        PersonStatus.determine(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void determine_overlimit_input() {
        PersonStatus.determine(1000);
    }

    @Test
    public void determine() {
        Assert.assertEquals(PersonStatus.UNDEFINED, PersonStatus.determine(0));

        Assert.assertEquals(PersonStatus.BABY, PersonStatus.determine(1));
        Assert.assertEquals(PersonStatus.BABY, PersonStatus.determine(5));
        Assert.assertEquals(PersonStatus.BABY, PersonStatus.determine(10));

        Assert.assertEquals(PersonStatus.TEENAGER, PersonStatus.determine(11));
        Assert.assertEquals(PersonStatus.TEENAGER, PersonStatus.determine(15));
        Assert.assertEquals(PersonStatus.TEENAGER, PersonStatus.determine(20));

        Assert.assertEquals(PersonStatus.YOUNG, PersonStatus.determine(21));
        Assert.assertEquals(PersonStatus.YOUNG, PersonStatus.determine(25));
        Assert.assertEquals(PersonStatus.YOUNG, PersonStatus.determine(30));

        Assert.assertEquals(PersonStatus.ADULT, PersonStatus.determine(31));
        Assert.assertEquals(PersonStatus.ADULT, PersonStatus.determine(50));
        Assert.assertEquals(PersonStatus.ADULT, PersonStatus.determine(60));

        Assert.assertEquals(PersonStatus.RETIRED, PersonStatus.determine(61));
        Assert.assertEquals(PersonStatus.RETIRED, PersonStatus.determine(70));
        Assert.assertEquals(PersonStatus.RETIRED, PersonStatus.determine(80));
        Assert.assertEquals(PersonStatus.RETIRED, PersonStatus.determine(90));
        Assert.assertEquals(PersonStatus.RETIRED, PersonStatus.determine(100));

        Assert.assertEquals(PersonStatus.DUNCAN_MAC_LEOD, PersonStatus.determine(101));
    }

}