package edu.kit.ipd.sdq.probespec.pcm.test;

public class TestResponseTimeCalculator {

// Test Sketch:
//
//    testResponseTimeCalculator() {
//
//        startProbe = ...
//        stopProbe = ...
//
//        responseTimeProbe = ...
//        register(DIfferenceCalculator(responseTimeProbe)).bind(startProbe, stopProbe)
//
//        rootCtx = new Ctx("root");
//        childCtx = new Ctx("child", rootCtx)
//        
//
//      //////////////////////////////////////
//        add(10,startProbe)
//      //////////////////////////////////////
//
//        add(12, stopProbe)
//        Assert(responseTimeProbe = 2)
//
//        add(14, stopProbe, rootCtx)
//        Assert(responseTimeProbe = 4)
//
//        add(16, stopProbe, childCtx)
//        Assert(responseTimeProbe = 6)
//
//      //////////////////////////////////////
//        add(20,startProbe, rootCtx)
//      //////////////////////////////////////
//
//        add(22,stopProbe)
//        Assert(responseTimeProbe = 12)
//
//        add(24,stopProbe, rootCtx)
//        Assert(responseTimeProbe = 4)
//
//        add(26,stopProbe, childCtx)
//        Assert(responseTimeProbe = 6)
//
//      //////////////////////////////////////
//        add(30,startProbe, childCtx)
//      //////////////////////////////////////
//
//        add(32,stopProbe)
//        Assert(responseTimeProbe = 22)
//
//        add(34,stopProbe, rootCtx)
//        Assert(responseTimeProbe = 14)
//
//        add(36,stopProbe, childCtx)
//        Assert(responseTimeProbe = 6)
//
//      }
    
}
