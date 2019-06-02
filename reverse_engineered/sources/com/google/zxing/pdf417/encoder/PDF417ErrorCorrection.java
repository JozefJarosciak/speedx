package com.google.zxing.pdf417.encoder;

import android.support.v4.media.TransportMediator;
import android.support.v4.view.InputDeviceCompat;
import ch.qos.logback.core.net.SyslogConstants;
import com.alibaba.fastjson.asm.Opcodes;
import com.avos.avoscloud.AVException;
import com.baidu.lbsapi.auth.LBSAuthManager;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.google.zxing.WriterException;
import com.google.zxing.pdf417.PDF417Common;
import org.apache.http.HttpStatus;

final class PDF417ErrorCorrection {
    private static final int[][] EC_COEFFICIENTS = new int[][]{new int[]{27, 917}, new int[]{522, 568, 723, 809}, new int[]{237, 308, 436, 284, 646, 653, 428, 379}, new int[]{274, 562, 232, 755, 599, 524, 801, Opcodes.IINC, 295, AVException.OBJECT_TOO_LARGE, 442, 428, 295, 42, 176, 65}, new int[]{361, 575, 922, 525, 176, 586, 640, 321, 536, 742, 677, 742, 687, 284, Opcodes.INSTANCEOF, m_AppUI.MSG_CITY_SUP_DOM, 273, 494, 263, Opcodes.I2S, 593, 800, 571, 320, 803, 133, 231, 390, 685, 330, 63, HttpStatus.SC_GONE}, new int[]{539, HttpStatus.SC_UNPROCESSABLE_ENTITY, 6, 93, 862, 771, 453, 106, 610, 287, 107, 505, 733, 877, 381, 612, 723, 476, 462, Opcodes.IRETURN, 430, 609, 858, 822, 543, 376, 511, HttpStatus.SC_BAD_REQUEST, 672, 762, 283, 184, 440, 35, m_AppUI.MSG_SET_SENSOR_STATUS, 31, 460, 594, 225, 535, m_AppUI.MSG_CITY_SUP_DOM, 352, 605, Opcodes.IFLE, 651, 201, 488, 502, 648, 733, 717, 83, 404, 97, 280, 771, 840, 629, 4, 381, 843, 623, 264, 543}, new int[]{521, 310, 864, 547, 858, 580, 296, 379, 53, 779, 897, 444, HttpStatus.SC_BAD_REQUEST, 925, 749, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, 822, 93, 217, AVException.ACCOUNT_ALREADY_LINKED, PDF417Common.MAX_CODEWORDS_IN_BARCODE, 244, 583, 620, 246, Opcodes.LCMP, 447, 631, 292, 908, 490, 704, m_AppUI.MSG_CHINA_SUP_ITS, 258, 457, 907, 594, 723, 674, 292, 272, 96, 684, 432, 686, 606, 860, 569, Opcodes.INSTANCEOF, 219, 129, 186, 236, 287, Opcodes.CHECKCAST, 775, 278, 173, 40, 379, 712, 463, 646, 776, 171, 491, 297, 763, Opcodes.IFGE, 732, 95, 270, 447, 90, 507, 48, 228, 821, 808, 898, 784, 663, 627, 378, 382, 262, 380, LBSAuthManager.CODE_AUTHENTICATING, 754, 336, 89, 614, 87, 432, 670, 616, Opcodes.IFGT, 374, 242, 726, 600, 269, 375, 898, 845, 454, 354, TransportMediator.KEYCODE_MEDIA_RECORD, 814, 587, 804, 34, AVException.USER_DOESNOT_EXIST, 330, 539, 297, 827, 865, 37, m_AppUI.MSG_CITY_SUP_DOM, 834, 315, 550, 86, 801, 4, 108, 539}, new int[]{524, 894, 75, 766, 882, 857, 74, 204, 82, 586, 708, 250, 905, 786, 138, 720, 858, 194, 311, 913, 275, 190, 375, 850, 438, 733, 194, 280, 201, 280, 828, 757, 710, 814, 919, 89, 68, 569, 11, 204, 796, 605, 540, 913, 801, 700, 799, AVException.DUPLICATE_VALUE, 439, 418, 592, 668, 353, 859, 370, 694, 325, 240, 216, InputDeviceCompat.SOURCE_KEYBOARD, 284, 549, AVException.USER_ID_MISMATCH, 884, 315, 70, 329, 793, 490, 274, 877, 162, 749, 812, 684, 461, 334, 376, 849, 521, HttpStatus.SC_TEMPORARY_REDIRECT, 291, 803, 712, 19, 358, 399, 908, 103, 511, 51, 8, m_AppUI.MSG_CITY_SUP_DOM, 225, 289, 470, 637, 731, 66, 255, 917, 269, 463, 830, 730, 433, 848, 585, SyslogConstants.LOG_LOCAL1, 538, 906, 90, 2, 290, 743, Opcodes.IFNONNULL, 655, 903, 329, 49, 802, 580, 355, 588, 188, 462, 10, 134, 628, 320, 479, TransportMediator.KEYCODE_MEDIA_RECORD, 739, 71, 263, 318, 374, LBSAuthManager.CODE_UNAUTHENTICATE, Opcodes.CHECKCAST, 605, AVException.VALIDATION_ERROR, 673, 687, 234, 722, 384, Opcodes.RETURN, 752, 607, 640, 455, Opcodes.INSTANCEOF, 689, 707, 805, 641, 48, 60, 732, 621, 895, 544, 261, 852, 655, 309, 697, 755, 756, 60, 231, 773, 434, 421, 726, 528, 503, 118, 49, 795, 32, SyslogConstants.LOG_LOCAL2, 500, 238, 836, 394, 280, 566, 319, 9, 647, 550, 73, 914, 342, 126, 32, 681, 331, 792, 620, 60, 609, 441, Opcodes.GETFIELD, 791, 893, 754, 605, 383, 228, 749, 760, AVException.USER_WITH_MOBILEPHONE_NOT_FOUND, 54, 297, 134, 54, 834, 299, 922, 191, 910, 532, 609, 829, 189, 20, 167, 29, 872, 449, 83, HttpStatus.SC_PAYMENT_REQUIRED, 41, 656, 505, 579, 481, 173, 404, AVException.INVALID_LINKED_SESSION, 688, 95, 497, 555, 642, 543, HttpStatus.SC_TEMPORARY_REDIRECT, Opcodes.IF_ICMPEQ, 924, 558, 648, 55, 497, 10}, new int[]{352, 77, 373, 504, 35, 599, 428, 207, HttpStatus.SC_CONFLICT, 574, 118, 498, 285, 380, 350, 492, 197, 265, 920, Opcodes.IFLT, 914, 299, 229, 643, 294, 871, 306, 88, 87, Opcodes.INSTANCEOF, 352, 781, 846, 75, 327, m_AppUI.MSG_PLACEFIELD_RELOAD, 435, 543, 203, 666, 249, 346, 781, 621, 640, 268, 794, 534, 539, 781, HttpStatus.SC_REQUEST_TIMEOUT, 390, 644, 102, 476, 499, 290, 632, 545, 37, 858, 916, 552, 41, 542, 289, AVException.INVALID_FILE_NAME, 272, 383, 800, 485, 98, 752, 472, 761, 107, 784, 860, 658, 741, 290, 204, 681, HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED, 855, 85, 99, 62, 482, Opcodes.GETFIELD, 20, 297, 451, 593, 913, AVException.VALIDATION_ERROR, 808, 684, 287, 536, 561, 76, 653, 899, 729, 567, 744, 390, 513, Opcodes.CHECKCAST, m_AppUI.MSG_CHINA_SUP_ITS, 258, 240, m_AppUI.MSG_COMPASS_DISPLAY, 794, 395, 768, 848, 51, 610, 384, 168, 190, 826, 328, 596, 786, HttpStatus.SC_SEE_OTHER, 570, 381, HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, 641, Opcodes.IFGE, 237, Opcodes.DCMPL, 429, 531, 207, 676, 710, 89, 168, HttpStatus.SC_NOT_MODIFIED, HttpStatus.SC_PAYMENT_REQUIRED, 40, 708, 575, 162, 864, 229, 65, 861, 841, 512, Opcodes.IF_ICMPLE, 477, 221, 92, 358, 785, 288, 357, 850, 836, 827, 736, 707, 94, 8, 494, 114, 521, 2, 499, 851, 543, 152, 729, 771, 95, 248, 361, 578, 323, 856, 797, 289, 51, 684, 466, 533, 820, 669, 45, 902, 452, 167, 342, 244, 173, 35, 463, 651, 51, 699, 591, 452, 578, 37, AVException.TIMEOUT, 298, 332, 552, 43, 427, AVException.OPERATION_FORBIDDEN, 662, 777, 475, 850, 764, 364, 578, 911, 283, 711, 472, HttpStatus.SC_METHOD_FAILURE, 245, 288, 594, 394, 511, 327, 589, 777, 699, 688, 43, HttpStatus.SC_REQUEST_TIMEOUT, 842, 383, 721, 521, 560, 644, 714, 559, 62, Opcodes.I2B, 873, 663, 713, Opcodes.IF_ICMPEQ, 672, 729, 624, 59, Opcodes.INSTANCEOF, HttpStatus.SC_EXPECTATION_FAILED, Opcodes.IFLE, AVException.USER_ID_MISMATCH, 563, 564, 343, 693, 109, 608, 563, 365, Opcodes.PUTFIELD, 772, 677, 310, 248, 353, 708, HttpStatus.SC_GONE, 579, 870, 617, 841, 632, 860, 289, 536, 35, 777, 618, 586, HttpStatus.SC_FAILED_DEPENDENCY, 833, 77, 597, 346, 269, 757, 632, 695, 751, 331, 247, 184, 45, 787, 680, 18, 66, HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED, 369, 54, 492, 228, 613, 830, 922, 437, m_AppUI.MSG_SET_SENSOR_STATUS, 644, 905, 789, HttpStatus.SC_METHOD_FAILURE, HttpStatus.SC_USE_PROXY, 441, 207, 300, 892, 827, AVException.SCRIPT_ERROR, 537, 381, 662, 513, 56, AVException.UNSUPPORTED_SERVICE, 341, 242, 797, 838, 837, 720, 224, HttpStatus.SC_TEMPORARY_REDIRECT, 631, 61, 87, 560, 310, 756, 665, 397, 808, 851, 309, 473, 795, 378, 31, 647, 915, 459, 806, 590, 731, 425, 216, 548, 249, 321, 881, 699, 535, 673, 782, AVException.USERNAME_PASSWORD_MISMATCH, 815, 905, HttpStatus.SC_SEE_OTHER, 843, 922, 281, 73, 469, 791, 660, 162, 498, 308, Opcodes.IFLT, HttpStatus.SC_UNPROCESSABLE_ENTITY, 907, 817, Opcodes.NEW, 62, 16, 425, 535, 336, 286, 437, 375, 273, 610, 296, Opcodes.INVOKESPECIAL, 923, AVException.OBJECT_TOO_LARGE, 667, 751, 353, 62, 366, 691, 379, 687, 842, 37, 357, 720, 742, 330, 5, 39, 923, 311, HttpStatus.SC_FAILED_DEPENDENCY, 242, 749, 321, 54, 669, 316, 342, 299, 534, 105, 667, 488, 640, 672, 576, 540, 316, 486, 721, 610, 46, 656, 447, 171, 616, 464, 190, 531, 297, 321, 762, 752, 533, 175, 134, 14, 381, 433, 717, 45, 111, 20, 596, 284, 736, 138, 646, HttpStatus.SC_LENGTH_REQUIRED, 877, 669, AVException.SCRIPT_ERROR, 919, 45, 780, HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED, Opcodes.IF_ICMPLE, 332, 899, Opcodes.IF_ACMPEQ, 726, 600, 325, 498, 655, 357, 752, 768, 223, 849, 647, 63, 310, 863, AVException.INVALID_LINKED_SESSION, 366, HttpStatus.SC_NOT_MODIFIED, 282, 738, 675, HttpStatus.SC_GONE, 389, 244, 31, AVException.INVALID_NESTED_KEY, HttpStatus.SC_SEE_OTHER, 263}};

    private PDF417ErrorCorrection() {
    }

    static int getErrorCorrectionCodewordCount(int i) {
        if (i >= 0 && i <= 8) {
            return 1 << (i + 1);
        }
        throw new IllegalArgumentException("Error correction level must be between 0 and 8!");
    }

    static int getRecommendedMinimumErrorCorrectionLevel(int i) throws WriterException {
        if (i <= 0) {
            throw new IllegalArgumentException("n must be > 0");
        } else if (i <= 40) {
            return 2;
        } else {
            if (i <= 160) {
                return 3;
            }
            if (i <= 320) {
                return 4;
            }
            if (i <= 863) {
                return 5;
            }
            throw new WriterException("No recommendation possible");
        }
    }

    static String generateErrorCorrection(CharSequence charSequence, int i) {
        int i2;
        int errorCorrectionCodewordCount = getErrorCorrectionCodewordCount(i);
        char[] cArr = new char[errorCorrectionCodewordCount];
        int length = charSequence.length();
        for (int i3 = 0; i3 < length; i3++) {
            int charAt = (charSequence.charAt(i3) + cArr[cArr.length - 1]) % PDF417Common.NUMBER_OF_CODEWORDS;
            for (i2 = errorCorrectionCodewordCount - 1; i2 >= 1; i2--) {
                cArr[i2] = (char) (((929 - ((EC_COEFFICIENTS[i][i2] * charAt) % PDF417Common.NUMBER_OF_CODEWORDS)) + cArr[i2 - 1]) % PDF417Common.NUMBER_OF_CODEWORDS);
            }
            cArr[0] = (char) ((929 - ((EC_COEFFICIENTS[i][0] * charAt) % PDF417Common.NUMBER_OF_CODEWORDS)) % PDF417Common.NUMBER_OF_CODEWORDS);
        }
        StringBuilder stringBuilder = new StringBuilder(errorCorrectionCodewordCount);
        for (i2 = errorCorrectionCodewordCount - 1; i2 >= 0; i2--) {
            if (cArr[i2] != '\u0000') {
                cArr[i2] = (char) (929 - cArr[i2]);
            }
            stringBuilder.append(cArr[i2]);
        }
        return stringBuilder.toString();
    }
}
