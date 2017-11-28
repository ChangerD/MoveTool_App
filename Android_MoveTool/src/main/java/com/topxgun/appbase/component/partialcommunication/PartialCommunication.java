package com.topxgun.appbase.component.partialcommunication;

import android.text.TextUtils;

import java.util.HashMap;

/**
 * Created by JWDING on 2017/7/27.
 */

public class PartialCommunication {

    public static HashMap<String,FunctionNoParamNoResult> functionsNoParamNoResult;
    public static HashMap<String,FunctionWithParamNoResult> functionsWithParamNoResult;
    public static HashMap<String,FunctionNoParamWithResult> functionsNoParamWithResult;
    public static HashMap<String,FunctionWithParamWithResult> functionsWithParamWithResult;

    public static class Function{
        String functionName;
        public Function(String functionName){
            this.functionName=functionName;
        }
    }

    public abstract static class FunctionNoParamNoResult extends Function{

        public FunctionNoParamNoResult(String functionName) {
            super(functionName);
        }

        public abstract void function();
    }

    public abstract static class FunctionWithParamNoResult<Params> extends Function{

        public FunctionWithParamNoResult(String functionName) {
            super(functionName);
        }

        public abstract void function(Params params);
    }

    public abstract static class FunctionNoParamWithResult<Result> extends Function{

        public FunctionNoParamWithResult(String functionName) {
            super(functionName);
        }

        public abstract Result function();
    }

    public abstract static class FunctionWithParamWithResult<Params,Result> extends Function{

        public FunctionWithParamWithResult(String functionName) {
            super(functionName);
        }

        public abstract Result function(Params params);

    }

    public void addFunction(FunctionNoParamNoResult functionNoParamNoResult){
        if(functionNoParamNoResult!=null){
            if(functionsNoParamNoResult==null){
                functionsNoParamNoResult=new HashMap<>();
            }
            functionsNoParamNoResult.put(functionNoParamNoResult.functionName,functionNoParamNoResult);
        }
    }

    public void addFunction(FunctionWithParamNoResult functionWithParamNoResult){
        if(functionWithParamNoResult!=null){
            if(functionsWithParamNoResult==null){
                functionsWithParamNoResult=new HashMap<>();
            }
            functionsWithParamNoResult.put(functionWithParamNoResult.functionName,functionWithParamNoResult);
        }
    }

    public void addFunction(FunctionNoParamWithResult functionNoParamWithResult){
        if(functionNoParamWithResult!=null){
            if(functionsNoParamWithResult==null){
                functionsNoParamWithResult=new HashMap<>();
            }
            functionsNoParamWithResult.put(functionNoParamWithResult.functionName,functionNoParamWithResult);
        }
    }

    public void addFunction(FunctionWithParamWithResult functionWithParamWithResult){
        if(functionWithParamWithResult!=null){
            if(functionsWithParamWithResult==null){
                functionsWithParamWithResult=new HashMap<>();
            }
            functionsWithParamWithResult.put(functionWithParamWithResult.functionName,functionWithParamWithResult);
        }
    }

    public void invokeFunction(String functionName){
        if(TextUtils.isEmpty(functionName)){
            return;
        }
        if(functionsNoParamNoResult!=null){
            FunctionNoParamNoResult functionNoParamNoResult=functionsNoParamNoResult.get(functionName);
            if(functionNoParamNoResult!=null){
                functionNoParamNoResult.function();
            }else{

            }
        }
    }

    public <Params> void invokeFunction(String functionName,Params params) {
        if(TextUtils.isEmpty(functionName)){
            return;
        }
        if(functionsWithParamNoResult!=null){
            FunctionWithParamNoResult functionWithParamNoResult=functionsWithParamNoResult.get(functionName);
            if(functionWithParamNoResult!=null){
                functionWithParamNoResult.function(params);
            }else{

            }
        }
    }

    public <Result> Result invokeFunction(String functionName,Class<Result> c){
        if(TextUtils.isEmpty(functionName)){
            return null;
        }
        if(functionsNoParamWithResult!=null){
            FunctionNoParamWithResult functionNoParamWithResult=functionsNoParamWithResult.get(functionName);
            if(functionNoParamWithResult!=null){
                if(c!=null){
                    return c.cast(functionNoParamWithResult.function());
                }else{
                    return (Result) functionNoParamWithResult.function();
                }
            }else{

            }
        }
        return null;
    }

    public <Params,Result> Result invokeFunction(String functionName,Params params,Class<Result> c){
        if(TextUtils.isEmpty(functionName)){
            return null;
        }
        if(functionsWithParamWithResult!=null){
            FunctionWithParamWithResult functionWithParamWithResult=functionsWithParamWithResult.get(functionName);
            if(functionWithParamWithResult!=null){
                if(c!=null){
                    return c.cast(functionWithParamWithResult.function(params));
                }else{
                    return (Result) functionWithParamWithResult.function(params);
                }
            }else{

            }
        }
        return null;
    }

    public void release(){
        functionsNoParamNoResult=null;
        functionsNoParamWithResult=null;
        functionsWithParamNoResult=null;
        functionsWithParamWithResult=null;
    }

}
