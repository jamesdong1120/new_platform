<trnCode>EnXfer</trnCode>
--body--
<trnId>${d.TRANNO}</trnId>
<cltcookie>${d.TRANNO}</cltcookie>
<insId>${d.TRANNO}</insId>
<acntNo>${d.ACCNO}</acntNo>
<acntToNo>${d.OPPACCNO}</acntToNo>
<amount>${m.money.fmt2(d.AMOUNT)}</amount>
<payType>0</payType>
<explain>${d.MEMO}</explain>
<actDate>${m.date.fmt('yyyy-MM-dd')}</actDate>
