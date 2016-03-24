<trnCode>Epay</trnCode>
--body--
<trnid>${d.TRANNO}</trnid>
<cltcookie></cltcookie>
<insId>${d.TRANNO}</insId>
<liac>${d.AGENTACCNO}</liac>
<acntNo>96--${d.ACCNO}</acntNo>
<acntName>${d.ACCNAME}</acntName>
<acntToNo>${d.OPPACCNO}</acntToNo>		
<acntToName>${d.OPPNAME}</acntToName>		
<externBank>${d.BANK_FLAG}</externBank>
<localFlag><#if d.BANK_FLAG == "0" >${d.AREA_FLAG}<#else>
<#if m.money.isLarge(d.AMOUNT)>3<#else>2</#if></#if></localFlag>
<bankCode>${d.BRANCHCODE}</bankCode>
<bankName>${d.BRANCHNAME}</bankName>
<bankAddr>µÿ÷∑</bankAddr>
<amount>${m.money.fmt2(d.AMOUNT)}</amount>
<crFlag>1</crFlag>
<explain>${d.PURPOSE}</explain>