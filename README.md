Use Swagger UI to call apis,

/createCampaign example:

{
  "name": "Sample Campaign 1",
  "startDate": "2024-03-24",
  "products": [
    {
      "id": 1
    },
    {
      "id": 2
    },

  ],
  "bid": 100
}

records are stored using h2 in memory database , use h2-console with application.properties credenetials to interact with tables and relationships.
