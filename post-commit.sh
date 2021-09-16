SERVER=http://localhost:8111/
ACCESS_TOKEN="eyJ0eXAiOiAiVENWMiJ9.cXlUdFhMQm9JempoTHh4OHlicWJla1duRmdz.YTBkNGFmMDktZWU2My00OTViLThhMmEtMmFiNTA2MTU0MmY2"

LOCATOR=$1

# The following is one-line:
(sleep 10;  curl --header "Authorization: Bearer $ACCESS_TOKEN" -X POST "$SERVER/app/rest/vcs-root-instances/commitHookNotification?locator=$LOCATOR" -o /dev/null) >/dev/null 2>&1 <&1 &

exit 0