package ${packageName}.common;

/**
* @Description: TODO
**/

public final class ShiftExp {
    private ShiftExp() {
    }

    public static void fatal(RestStatus status) {
        throw new BizException(status);
    }
}