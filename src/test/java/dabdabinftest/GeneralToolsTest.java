package dabdabinftest;

import org.junit.Assert;
import org.junit.Test;
import dabdabinf.tools.General;

public class GeneralToolsTest
{
    @Test
    public void shouldReadEntireResource()
    {
        Assert.assertEquals("This is an example resource.\nSecond line!",
            General.readEntireResource("/example_resource"));
    }
}
