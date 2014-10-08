require 'java'
java_import 'org.gaem.core.engine.script.ScriptAPI'

def begin_interact

end

def end_interact

end

def init

end

def method_missing(m, *args)
  ScriptAPI.send m, *args
end