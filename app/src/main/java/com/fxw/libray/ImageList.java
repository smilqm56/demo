package com.fxw.libray;

import java.util.Random;

/**
 * @author xianwen.fu
 * @version v1.0
 * @Title
 * @Description:
 * @Date 2016/4/25 0025 上午 11:42
 */
public class ImageList {

    static String[] image = {"http://f.hiphotos.baidu.com/image/h%3D360/sign=f82f0bb9b0b7d0a264c9029bfbef760d/b2de9c82d158ccbf399ad2311bd8bc3eb1354192.jpg",
            "http://e.hiphotos.baidu.com/image/h%3D360/sign=ec1c315097cad1c8cfbbfa214f3f67c4/83025aafa40f4bfb27bfbf2b014f78f0f7361865.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D360/sign=86aee1fbf1deb48fe469a7d8c01e3aef/b812c8fcc3cec3fd8757dcefd488d43f8794273a.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D360/sign=6035c56d3c01213fd03348da64e636f8/fc1f4134970a304ec83a514ed4c8a786c9175c6e.jpg",
            "http://c.hiphotos.baidu.com/image/h%3D360/sign=c045cbc3d5ca7bcb627bc1298e096b3f/a2cc7cd98d1001e98b4ae60fba0e7bec54e797b8.jpg",
            "http://g.hiphotos.baidu.com/image/h%3D360/sign=b85d9eb8087b020813c939e752d9f25f/14ce36d3d539b600eca8b9aaeb50352ac65cb74d.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D360/sign=5cc3b726596034a836e2be87fb1249d9/7c1ed21b0ef41bd56888d6d354da81cb39db3d39.jpg",
            "http://b.hiphotos.baidu.com/image/h%3D360/sign=a02c25b6cb177f3e0f34fa0b40ce3bb9/4afbfbedab64034f09147506adc379310b551dd0.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D360/sign=ee18f985cbea15ce5eeee60f86003a25/9c16fdfaaf51f3de59ac2e6496eef01f3a297990.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D360/sign=356da4eab11c8701c9b6b4e0177f9e6e/0d338744ebf81a4c4a11f6c7d52a6059252da698.jpg",
            "http://c.hiphotos.baidu.com/image/h%3D360/sign=4402b247d8b44aed464eb8e2831c876a/bf096b63f6246b60ce290379e9f81a4c510fa29c.jpg",
            "http://a.hiphotos.baidu.com/image/h%3D360/sign=a2eb7a0eb6de9c82b965ff895c8080d2/d1160924ab18972be4b49efde3cd7b899e510a7e.jpg",
            "http://c.hiphotos.baidu.com/image/h%3D360/sign=2bf4c65457fbb2fb2b2b5e147f4a2043/a044ad345982b2b7a2b8f7cd33adcbef76099b90.jpg",
            "http://b.hiphotos.baidu.com/image/h%3D360/sign=082d55c95066d0166119982ea72ad498/8601a18b87d6277fafd9e43c2a381f30e824fcef.jpg",
            "http://e.hiphotos.baidu.com/image/h%3D360/sign=cbf11f9bdb33c895b97e9e7de1127397/1c950a7b02087bf4ce043fe5f0d3572c10dfcfd6.jpg",
            "http://a.hiphotos.baidu.com/image/h%3D360/sign=4b1738258501a18befeb1449ae2e0761/8644ebf81a4c510f193a5c136259252dd42aa539.jpg",
            "http://a.hiphotos.baidu.com/image/h%3D360/sign=114df71e5e6034a836e2be87fb1249d9/7c1ed21b0ef41bd5250696eb53da81cb39db3d53.jpg",
            "http://h.hiphotos.baidu.com/image/h%3D360/sign=c01044277d3e6709a10043f90bc69fb8/faedab64034f78f090a022f37b310a55b3191c02.jpg",
    };

    public static String getImagerUrl(){
        return image[new Random().nextInt(image.length)];
    }
}
