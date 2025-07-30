#!MANAGED-CONFIG ${host}/subscription?urls=<#assign urlList=(urls?join("&urls="))>${urlList} interval=86400 strict=false

[General]
loglevel = notify
dns-server = 119.29.29.29, 223.6.6.6, 8.8.8.8, system
skip-proxy = 127.0.0.1, 192.168.0.0/16, 10.0.0.0/8, 172.16.0.0/12, 100.64.0.0/10, 17.0.0.0/8, localhost, *.local, *.crashlytics.com
external-controller-access = MixChina@127.0.0.1:8233
allow-wifi-access = true
enhanced-mode-by-rule = false
exclude-simple-hostnames = true
ipv6 = false
replica = false
internet-test-url = http://baidu.com
proxy-test-url = http://bing.com
test-timeout = 3
http-listen=0.0.0.0:8234
socks-listen=0.0.0.0:8235

[Proxy]
DIRECT = direct
<#list proxyNodes as node>
${node}
</#list>

[Proxy Group]
Proxy = select,<#assign namesJoined=(proxyNodeNames?join(",")) /> ${namesJoined}
Apple = select, DIRECT, Proxy
Domestic = select, DIRECT, Proxy
Other = select, Proxy, DIRECT

[Rule]
RULE-SET,SYSTEM,DIRECT
RULE-SET,https://codeberg.org/zihluwang/surge-rulesets/raw/branch/main/apple.list,DIRECT
RULE-SET,https://codeberg.org/zihluwang/surge-rulesets/raw/branch/main/microsoft.list,DIRECT
RULE-SET,https://codeberg.org/zihluwang/surge-rulesets/raw/branch/main/jetbrains.list,Proxy
RULE-SET,https://codeberg.org/zihluwang/surge-rulesets/raw/branch/main/domestic.list,DIRECT
RULE-SET,https://codeberg.org/zihluwang/surge-rulesets/raw/branch/main/domestic-media.list,DIRECT
RULE-SET,https://codeberg.org/zihluwang/surge-rulesets/raw/branch/main/overseas-media.list,Proxy
RULE-SET,https://codeberg.org/zihluwang/surge-rulesets/raw/branch/main/proxy.list,Proxy
RULE-SET,https://codeberg.org/zihluwang/surge-rulesets/raw/branch/main/reject.list,REJECT
RULE-SET,LAN,DIRECT
GEOIP,CN,DIRECT
FINAL,Proxy,dns-failed
