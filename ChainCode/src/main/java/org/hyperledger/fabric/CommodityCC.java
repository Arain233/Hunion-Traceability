package org.hyperledger.fabric;


import com.owlike.genson.Genson;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.*;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;


@Contract(
        name = "CommodityCC",
        info = @Info
                (
                title = "Commodity contract",
                description = "The hyperlegendary Commodity contract",
                version = "1.0.0-SNAPSHOT",
                license = @License(
                        name = "Apache 2.0 License",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html")
                )
        )
@Default
public  class CommodityCC implements ContractInterface {

    private  Genson genson = new Genson();

    @Transaction()
    public Commodity query(final Context ctx, final String key) {
        ChaincodeStub stub = ctx.getStub();
        String CommState = stub.getStringState(key);
        if (CommState.isEmpty())
        {
            throw new RuntimeException("The commodity does not exist");
        }
        Commodity commodity = genson.deserialize(CommState, Commodity.class);

        return commodity;
    }


    @Transaction()
    public void initLedger(final Context ctx) {
        ChaincodeStub stub = ctx.getStub();
        String MedData ="{\"digest\":\"woshizhazhahui\",\"id\":\"12352552\"}";
        Commodity commodity = genson.deserialize(MedData, Commodity.class);
        String medState = genson.serialize(commodity);
        stub.putStringState(commodity.getId(), medState);
        }

    @Transaction()
    public void insert(final Context ctx, final String id,final String digest)
    {
        ChaincodeStub stub = ctx.getStub();
        String commState = stub.getStringState(id);
        if (!commState.isEmpty())
        {
            throw new RuntimeException("The commodity already exists");
        }
        Commodity commodity = new Commodity(id,digest);
        commState = genson.serialize(commodity);
        stub.putStringState(id, commState);
    }

    @Transaction()
    public void update(final Context ctx, final String id, final String newDigest) {
        ChaincodeStub stub = ctx.getStub();
        String commState = stub.getStringState(id);
        if (commState.isEmpty())
        {
            throw new RuntimeException("The commodity does not exist");
        }
        Commodity newCommodity = new Commodity(id,newDigest);
        String newCommState = genson.serialize(newCommodity);
        stub.putStringState(id, newCommState);
    }

    @Transaction()
    public void delete(final Context ctx, final String id)
    {
        ChaincodeStub stub=ctx.getStub();
        String commState=stub.getStringState(id);
        if(commState.isEmpty())
        {
            throw new RuntimeException("The commodity does not exist");
        }
        stub.delState(id);
    }
}
